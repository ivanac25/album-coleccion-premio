package com.album.service;

import com.album.model.*;
import com.album.model.reward.PremioContext;
import com.album.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class PremioService {

    private final AlbumRepository albumRepo;
    private final RewardRepository rewardRepo;
    private final UserRewardRepository userRewardRepo;
    private final ColeccionService coleccionService; // para verificar progreso

    public PremioService(
            AlbumRepository albumRepo,
            RewardRepository rewardRepo,
            UserRewardRepository userRewardRepo,
            ColeccionService coleccionService
    ) {
        this.albumRepo = albumRepo;
        this.rewardRepo = rewardRepo;
        this.userRewardRepo = userRewardRepo;
        this.coleccionService = coleccionService;
    }

    @Transactional
    public Object reclamarPremio(Long userId, Long albumId) {
        // 1️⃣ Verificar progreso real del álbum
        var resumen = coleccionService.getResumenColeccion(userId, albumId);

        double progreso = 0.0;
        if (resumen != null && resumen.containsKey("progreso")) {
            Object p = resumen.get("progreso");
            if (p instanceof Number) {
                progreso = ((Number) p).doubleValue();
            }
        }

        // Si el álbum no está completo
        if (progreso < 1.0) {
            return Map.of("error", "El premio no está disponible para reclamar. El álbum no está completo.");
        }

        // 2️⃣ Buscar entidades
        Album album = albumRepo.findById(albumId)
                .orElseThrow(() -> new IllegalStateException("Álbum no encontrado"));

        Reward reward = rewardRepo.findByAlbum(album)
                .orElseThrow(() -> new IllegalStateException("Álbum sin premio asociado"));

        UserReward ur = userRewardRepo.findByUsuarioIdAndAlbumId(userId, albumId)
                .orElseGet(() -> {
                    UserReward nuevo = new UserReward();
                    nuevo.setUsuario(null); // simplificado
                    nuevo.setAlbum(album);
                    nuevo.setReward(reward);
                    nuevo.setStatus(UserReward.RewardStatus.DISPONIBLE); // ✅ cambio clave
                    return userRewardRepo.save(nuevo);
                });

        try {
            // 3️⃣ Aplicar patrón State
            PremioContext ctx = new PremioContext(ur);
            ctx.reclamar(); // ✅ coincide con tu método
            userRewardRepo.save(ur);

            // 4️⃣ Respuesta OK
            return Map.of(
                    "album", album.getTitulo(),
                    "tipo", reward.getTipo(),
                    "estado", ur.getStatus().name() // ✅ campo correcto
            );

        } catch (IllegalStateException e) {
            return Map.of("error", e.getMessage());
        } catch (Exception e) {
            return Map.of("error", "Error interno al reclamar el premio: " + e.getMessage());
        }
    }

        @Transactional
    public Object entregarPremio(Long userId, Long albumId) {
        // Buscar el registro del premio del usuario
        UserReward ur = userRewardRepo.findByUsuarioIdAndAlbumId(userId, albumId)
                .orElseThrow(() -> new IllegalStateException("El usuario no tiene un premio asociado a este álbum"));

        try {
            // Usamos el patrón State
            PremioContext ctx = new PremioContext(ur);
            ctx.entregarPremio(); // 👈 este método lo agregaste en PremioContext
            userRewardRepo.save(ur);

            // Respuesta exitosa
            return Map.of(
                    "album", ur.getAlbum().getTitulo(),
                    "estado", ur.getStatus().name()
            );

        } catch (IllegalStateException e) {
            // Errores de flujo lógico (por ejemplo, si no estaba reclamado aún)
            return Map.of("error", e.getMessage());
        } catch (Exception e) {
            // Errores imprevistos
            return Map.of("error", "Error interno al entregar el premio: " + e.getMessage());
        }
    }

}
