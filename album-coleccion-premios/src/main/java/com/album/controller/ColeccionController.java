package com.album.controller;

import com.album.service.ColeccionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/collection")
public class ColeccionController {

    private final ColeccionService service;

    public ColeccionController(ColeccionService service) {
        this.service = service;
    }

    // 📊 Endpoint: GET /api/collection/resumen?userId=1&albumId=1
    @GetMapping("/resumen")
    public Map<String, Object> resumen(@RequestParam Long userId, @RequestParam Long albumId) {
        return service.getResumenColeccion(userId, albumId);
    }
}
