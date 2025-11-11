package com.album.model.reward;

public interface IEstadoPremio {
    void reclamar(PremioContext ctx);

    // 🟢 nuevo método
    default void entregar(PremioContext ctx) {
        throw new IllegalStateException("El premio no puede ser entregado en este estado.");
    }
}
