package com.psoft.entregas.model;

import com.psoft.entregas.strategy.ModalidadeEntrega;

import java.math.BigDecimal;

public class Pedido {

    private final int id;
    private final String cliente;
    private final String enderecoOrigem;
    private final String enderecoDestino;
    private final double peso;
    private final double distanciaKm;

    private ModalidadeEntrega modalidade;
    private StatusPedido status;

    public Pedido(
            int id,
            String cliente,
            String enderecoOrigem,
            String enderecoDestino,
            double peso,
            double distanciaKm,
            ModalidadeEntrega modalidade
    ) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "O identificador deve ser positivo."
            );
        }

        if (cliente == null || cliente.isBlank()) {
            throw new IllegalArgumentException(
                    "O cliente deve ser informado."
            );
        }

        if (enderecoOrigem == null || enderecoOrigem.isBlank()
                || enderecoDestino == null || enderecoDestino.isBlank()) {
            throw new IllegalArgumentException(
                    "Os endereços devem ser informados."
            );
        }

        if (!Double.isFinite(peso) || peso <= 0
                || !Double.isFinite(distanciaKm) || distanciaKm <= 0) {
            throw new IllegalArgumentException(
                    "Peso e distância devem ser positivos e válidos."
            );
        }

        if (modalidade == null) {
            throw new IllegalArgumentException(
                    "A modalidade deve ser informada."
            );
        }

        this.id = id;
        this.cliente = cliente;
        this.enderecoOrigem = enderecoOrigem;
        this.enderecoDestino = enderecoDestino;
        this.peso = peso;
        this.distanciaKm = distanciaKm;
        this.modalidade = modalidade;
        this.status = StatusPedido.SOLICITADO;
    }

    public BigDecimal calcularCusto() {
        return modalidade.calcularCusto(distanciaKm);
    }

    public int calcularPrazo() {
        return modalidade.calcularPrazo(distanciaKm);
    }

    public void alterarModalidade(ModalidadeEntrega novaModalidade) {

        if (status != StatusPedido.SOLICITADO) {
            throw new IllegalStateException(
                    "Não é possível alterar a modalidade de um pedido "
                    + "que já foi despachado ou entregue."
            );
        }

        if (novaModalidade == null) {
            throw new IllegalArgumentException(
                    "A nova modalidade não pode ser nula."
            );
        }

        this.modalidade = novaModalidade;
    }

    public void despachar() {

        if (status != StatusPedido.SOLICITADO) {
            throw new IllegalStateException(
                    "Somente pedidos solicitados podem ser despachados."
            );
        }

        this.status = StatusPedido.DESPACHADO;
    }

    public void finalizarEntrega() {

        if (status != StatusPedido.DESPACHADO) {
            throw new IllegalStateException(
                    "Somente pedidos despachados podem ser entregues."
            );
        }

        this.status = StatusPedido.ENTREGUE;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public double getPeso() {
        return peso;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public ModalidadeEntrega getModalidade() {
        return modalidade;
    }

    public StatusPedido getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return "\n========== PEDIDO =========="
                + "\nID: " + id
                + "\nCliente: " + cliente
                + "\nOrigem: " + enderecoOrigem
                + "\nDestino: " + enderecoDestino
                + "\nPeso: " + peso + " kg"
                + "\nDistância: " + distanciaKm + " km"
                + "\nModalidade: " + modalidade.getNome()
                + "\nCusto: R$ " + calcularCusto().toPlainString()
                + "\nPrazo: " + calcularPrazo() + " dia(s)"
                + "\nStatus: " + status
                + "\n============================";
    }
}
