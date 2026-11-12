package com.psoft.entregas.service;

import com.psoft.entregas.model.Pedido;
import com.psoft.entregas.strategy.ModalidadeEntrega;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CentralLogistica {

    private final Map<Integer, Pedido> pedidos;

    private CentralLogistica() {
        pedidos = new LinkedHashMap<>();
    }

    private static class InstanciaHolder {

        private static final CentralLogistica INSTANCE =
                new CentralLogistica();
    }

    public static CentralLogistica getInstance() {
        return InstanciaHolder.INSTANCE;
    }

    public synchronized void registrarPedido(Pedido pedido) {

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "O pedido não pode ser nulo."
            );
        }

        if (pedidos.containsKey(pedido.getId())) {
            throw new IllegalArgumentException(
                    "Já existe um pedido com esse identificador."
            );
        }

        pedidos.put(pedido.getId(), pedido);
    }

    public synchronized Pedido buscarPedido(int id) {

        Pedido pedido = pedidos.get(id);

        if (pedido == null) {
            throw new IllegalArgumentException(
                    "Pedido não encontrado: " + id
            );
        }

        return pedido;
    }

    public synchronized void alterarModalidade(
            int id,
            ModalidadeEntrega modalidade
    ) {

        Pedido pedido = buscarPedido(id);
        pedido.alterarModalidade(modalidade);
    }

    public synchronized void despacharPedido(int id) {

        Pedido pedido = buscarPedido(id);
        pedido.despachar();
    }

    public synchronized void finalizarEntrega(int id) {

        Pedido pedido = buscarPedido(id);
        pedido.finalizarEntrega();
    }

    public synchronized List<Pedido> listarPedidos() {

        return Collections.unmodifiableList(
                new ArrayList<>(pedidos.values())
        );
    }
}
