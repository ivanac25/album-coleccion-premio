package com.album.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "albums")
public class Album {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private Integer totalFiguritas;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sticker> stickers = new HashSet<>();

    @OneToOne(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private Reward reward;
}
