package com.album.model.reward;

import com.album.model.UserReward;

public class PremioContext {
    private IEstadoPremio estado;
    private final UserReward reward;

    public PremioContext(UserReward reward) {
        this.reward = reward;
        switch (reward.getStatus()) {
            case DISPONIBLE -> this.estado = new PremioDisponible();
            case RECLAMADO -> this.estado = new PremioReclamado();
            case ENTREGADO -> this.estado = new PremioEntregado();
        }
    }

    public void reclamar() {
        estado.reclamar(this);
    }

    public void entregarPremio() {
        estado.entregar(this);
    }

    public IEstadoPremio getEstado() {
        return estado;
    }

    public void setEstado(IEstadoPremio nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public UserReward getReward() {
        return reward;
    }
}
