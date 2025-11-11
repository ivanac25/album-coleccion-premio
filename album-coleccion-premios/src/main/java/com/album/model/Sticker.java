package com.album.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "stickers")
public class Sticker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private Integer numero;

    @Enumerated(EnumType.STRING)
    private Rareza rareza = Rareza.COMUN;

    public enum Rareza { COMUN, RARA, EPICA }
}
