package com.album.model.reward;

public class PremioEntregado implements IEstadoPremio {
    @Override
    public void reclamar(PremioContext ctx) {
        throw new IllegalStateException("El premio ya fue entregado. No puede reclamarse nuevamente.");
    }

    @Override
    public void entregar(PremioContext ctx) {
        throw new IllegalStateException("El premio ya fue entregado.");
    }
}
