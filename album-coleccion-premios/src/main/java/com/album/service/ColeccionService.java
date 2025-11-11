package com.album.service;

import com.album.model.Album;
import com.album.repository.AlbumRepository;
import com.album.repository.StickerRepository;
import com.album.repository.UserStickerRepository;
import com.album.service.events.AlbumObserver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ColeccionService {

    private final AlbumRepository albumRepo;
    private final StickerRepository stickerRepo;
    private final UserStickerRepository userStickerRepo;
    private final List<AlbumObserver> observers;

    public ColeccionService(
            AlbumRepository albumRepo,
            StickerRepository stickerRepo,
            UserStickerRepository userStickerRepo,
            List<AlbumObserver> observers
    ) {
        this.albumRepo = albumRepo;
        this.stickerRepo = stickerRepo;
        this.userStickerRepo = userStickerRepo;
        this.observers = observers;
    }

    // 📊 Resumen general del álbum
    public Map<String, Object> getResumenColeccion(Long userId, Long albumId) {
        int totalFiguritas = stickerRepo.countByAlbumId(albumId);
        int obtenidas = userStickerRepo.countDistinctByUsuarioIdAndStickerAlbumId(userId, albumId);

        double progreso = totalFiguritas == 0 ? 0.0 : (double) obtenidas / totalFiguritas;

        // 🧮 Calcular faltantes y duplicadas
        var faltantes = stickerRepo.findIdsByAlbumId(albumId).stream()
                .filter(id -> !userStickerRepo.existsByUsuarioIdAndStickerId(userId, id))
                .toList();

        var duplicadas = userStickerRepo.findDuplicadas(userId, albumId);

        // ✅ Si el álbum está completo → notificar observers y mostrar mensaje
        if (progreso >= 1.0) {
            System.out.println("✅ Álbum completo detectado.");
            System.out.println("Observers registrados: " + observers.size());
            observers.forEach(o -> o.onAlbumCompleted(userId, albumId));

            // Obtenemos el título del álbum (si existe)
            String nombreAlbum = albumRepo.findById(albumId)
                    .map(Album::getTitulo)
                    .orElse("Álbum " + albumId);

            return Map.of(
                    "albumId", albumId,
                    "nombreAlbum", nombreAlbum,
                    "totalFiguritas", totalFiguritas,
                    "obtenidas", obtenidas,
                    "progreso", progreso,
                    "faltantes", faltantes,
                    "duplicadas", duplicadas,
                    "mensaje", "🎉 ¡Felicidades! Completaste el álbum \"" + nombreAlbum + "\" 🎉"
            );
        }

        // 🔄 Si no está completo → resumen normal
        return Map.of(
                "albumId", albumId,
                "totalFiguritas", totalFiguritas,
                "obtenidas", obtenidas,
                "progreso", progreso,
                "faltantes", faltantes,
                "duplicadas", duplicadas
        );
    }
}
