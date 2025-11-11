package com.album.model;

import jakarta.persistence.*;
import lombok.*;
import com.album.model.reward.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "user_rewards", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","album_id"}))
public class UserReward {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reward reward;

    @Enumerated(EnumType.STRING)
    private RewardStatus status = RewardStatus.DISPONIBLE;

    public enum RewardStatus { DISPONIBLE, RECLAMADO, ENTREGADO }

    public void reclamar() {
        if (status != RewardStatus.DISPONIBLE) {
            throw new IllegalStateException("El premio no está disponible para reclamar");
        }
        status = RewardStatus.RECLAMADO;
    }

    public void entregar() {
    if (status != RewardStatus.RECLAMADO) {
        throw new IllegalStateException("Solo se pueden entregar premios ya reclamados");
    }
    status = RewardStatus.ENTREGADO;
}

}
