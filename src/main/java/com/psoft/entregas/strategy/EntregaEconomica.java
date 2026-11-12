package com.psoft.entregas.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EntregaEconomica implements ModalidadeEntrega {

    private static final BigDecimal FATOR_PRECO =
            new BigDecimal("0.75");

    @Override
    public BigDecimal calcularCusto(double distanciaKm) {

        BigDecimal custoConvencional =
                new EntregaConvencional().calcularCusto(distanciaKm);

        return custoConvencional
                .multiply(FATOR_PRECO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int calcularPrazo(double distanciaKm) {

        return 3 + (int) Math.ceil(distanciaKm / 150.0);
    }

    @Override
    public String getNome() {
        return "Entrega Econômica";
    }
}
