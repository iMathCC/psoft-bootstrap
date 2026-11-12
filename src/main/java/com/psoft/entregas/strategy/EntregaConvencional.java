package com.psoft.entregas.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EntregaConvencional implements ModalidadeEntrega {

    private static final BigDecimal TARIFA_FIXA =
            new BigDecimal("15.00");

    private static final BigDecimal VALOR_POR_KM =
            new BigDecimal("0.80");

    @Override
    public BigDecimal calcularCusto(double distanciaKm) {

        BigDecimal distancia = BigDecimal.valueOf(distanciaKm);

        return TARIFA_FIXA.add(
                VALOR_POR_KM.multiply(distancia)
        ).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int calcularPrazo(double distanciaKm) {

        return Math.max(
                1,
                (int) Math.ceil(distanciaKm / 300.0)
        );
    }

    @Override
    public String getNome() {
        return "Entrega Convencional";
    }
}
