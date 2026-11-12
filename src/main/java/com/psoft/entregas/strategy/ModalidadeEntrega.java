package com.psoft.entregas.strategy;

import java.math.BigDecimal;

public interface ModalidadeEntrega {

    BigDecimal calcularCusto(double distanciaKm);

    int calcularPrazo(double distanciaKm);

    String getNome();
}
