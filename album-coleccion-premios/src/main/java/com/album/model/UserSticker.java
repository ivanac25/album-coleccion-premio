package com.album.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "user_stickers", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","sticker_id","idx"}))
public class UserSticker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sticker sticker;

    // Permite tener duplicadas diferenciadas
    private Integer idx = 0;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.EN_COLECCION;

    public enum Estado { EN_COLECCION, EN_TRADE }
}
