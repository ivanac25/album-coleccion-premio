package com.album.service.events;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NotificacionObserver implements AlbumObserver {

    @Override
    public void onAlbumCompleted(Long userId, Long albumId) {
        // Fecha y hora actual
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        // Mensaje con emojis y formato para presentación
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println(" [" + timestamp + "] ¡ÁLBUM COMPLETADO! 🎉");
        System.out.println("Usuario ID: " + userId);
        System.out.println(" Álbum ID: " + albumId);
        System.out.println(" Felicitaciones, completaste todo el album, reclama tu premio.");
        System.out.println("═══════════════════════════════════════════════════");
    }
}
