package com.album.controller;

import com.album.service.PremioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class PremioController {

    private final PremioService service;

    public PremioController(PremioService service) {
        this.service = service;
    }

    // Reclamar premio
    @PostMapping("/albums/{albumId}/claim")
    public ResponseEntity<?> claim(@PathVariable Long albumId, @RequestParam Long userId) {
        try {
            Object resultado = service.reclamarPremio(userId, albumId);

            if (resultado instanceof Map && ((Map<?, ?>) resultado).containsKey("error")) {
                return ResponseEntity.badRequest().body(resultado);
            }

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno al reclamar el premio: " + e.getMessage()));
        }
    }

    // 🎁 Nuevo: entregar premio
    @PostMapping("/{userId}/deliver")
    public ResponseEntity<?> deliver(@PathVariable Long userId, @RequestParam Long albumId) {
        try {
            Object resultado = service.entregarPremio(userId, albumId);

            if (resultado instanceof Map && ((Map<?, ?>) resultado).containsKey("error")) {
                return ResponseEntity.badRequest().body(resultado);
            }

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno al entregar el premio: " + e.getMessage()));
        }
    }
}
