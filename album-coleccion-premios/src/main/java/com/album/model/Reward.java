package com.album.model;

import jakarta.persistence.*;
import lombok.*;
import com.album.model.reward.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "rewards")
public class Reward {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="album_id", unique = true)
    private Album album;

    private String tipo; // p.ej. "MEDALLA", "WALLPAPER"
    @Column(columnDefinition = "TEXT")
    private String payloadJson;

    // State pattern embebido via UserReward
}
