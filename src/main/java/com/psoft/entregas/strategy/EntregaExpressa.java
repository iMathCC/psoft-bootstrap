package com.psoft.entregas.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EntregaExpressa implements ModalidadeEntrega {

    private static final BigDecimal TAXA_PRIORIDADE =
            new BigDecimal("25.00");

    @Override
    public BigDecimal calcularCusto(double distanciaKm) {

        BigDecimal custoConvencional =
                new EntregaConvencional().calcularCusto(distanciaKm);

        return custoConvencional
                .add(TAXA_PRIORIDADE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int calcularPrazo(double distanciaKm) {

        return Math.max(
                1,
                (int) Math.ceil(distanciaKm / 500.0)
        );
    }

    @Override
    public String getNome() {
        return "Entrega Expressa";
    }
}
