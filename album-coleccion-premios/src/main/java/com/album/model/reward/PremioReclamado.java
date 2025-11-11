package com.album.model.reward;

public class PremioReclamado implements IEstadoPremio {
    @Override
    public void reclamar(PremioContext ctx) {
        throw new IllegalStateException("El premio ya fue reclamado.");
    }

    @Override
    public void entregar(PremioContext ctx) {
        ctx.getReward().entregar(); // usa el método de UserReward
        ctx.setEstado(new PremioEntregado()); // cambia al nuevo estado
        System.out.println("[State] Premio entregado con éxito");
    }
}
