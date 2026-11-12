package com.psoft.entregas;

import com.psoft.entregas.model.Pedido;
import com.psoft.entregas.service.CentralLogistica;
import com.psoft.entregas.strategy.*;

public class Main {

    public static void main(String[] args) {

        // 1. Obter a central de logística
        CentralLogistica central = CentralLogistica.getInstance();

        // 2. Criar as modalidades
        ModalidadeEntrega convencional = new EntregaConvencional();
        ModalidadeEntrega expressa = new EntregaExpressa();
        ModalidadeEntrega economica = new EntregaEconomica();

        // 3. Criar os pedidos
        Pedido pedido1 = new Pedido(
                1,
                "Matheus",
                "Campina Grande - PB",
                "João Pessoa - PB",
                2.5,
                120,
                convencional
        );

        Pedido pedido2 = new Pedido(
                2,
                "João",
                "Campina Grande - PB",
                "Recife - PE",
                5.0,
                200,
                expressa
        );

        Pedido pedido3 = new Pedido(
                3,
                "Maria",
                "Campina Grande - PB",
                "Natal - RN",
                1.5,
                180,
                economica
        );

        // 4. Registrar os pedidos
        central.registrarPedido(pedido1);
        central.registrarPedido(pedido2);
        central.registrarPedido(pedido3);

        System.out.println("=== PEDIDOS CADASTRADOS ===");

        for (Pedido pedido : central.listarPedidos()) {
            System.out.println(pedido);
        }

        // 5. Alterar modalidade antes do despacho
        System.out.println("\n=== ALTERANDO MODALIDADE ===");

        central.alterarModalidade(1, expressa);

        System.out.println(central.buscarPedido(1));

        // 6. Despachar pedido
        System.out.println("\n=== DESPACHANDO PEDIDO ===");

        central.despacharPedido(1);

        System.out.println(central.buscarPedido(1));

        // 7. Tentar alterar modalidade após despacho
        System.out.println("\n=== TESTANDO BLOQUEIO ===");

        try {
            central.alterarModalidade(1, economica);

        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // 8. Finalizar entrega
        System.out.println("\n=== FINALIZANDO ENTREGA ===");

        central.finalizarEntrega(1);

        System.out.println(central.buscarPedido(1));

        // 9. Verificar Singleton
        System.out.println("\n=== TESTANDO SINGLETON ===");

        CentralLogistica outraReferencia =
                CentralLogistica.getInstance();

        System.out.println(
                "Mesma instância? " + (central == outraReferencia)
        );

        // 10. Relatório final
        System.out.println("\n=== RELATÓRIO FINAL ===");

        for (Pedido pedido : central.listarPedidos()) {
            System.out.println(pedido);
        }
    }
}
