package com.album.model.reward;

import com.album.model.UserReward;

public class PremioDisponible implements IEstadoPremio {
    @Override
    public void reclamar(PremioContext ctx) {
        UserReward ur = ctx.getReward();
        ur.reclamar();
        ctx.setEstado(new PremioReclamado());
    }
}
