# Sistema de Gerenciamento de Entregas

Implementação da Atividade 5 da disciplina Projeto de Software
2026.2 - Ciência da Computação (UFCG).

## Descrição

Sistema para gerenciamento de pedidos e modalidades de entrega
em uma empresa de comércio eletrônico.

O sistema permite cadastrar pedidos, selecionar modalidades,
calcular custos e prazos, alterar modalidades antes do despacho
e acompanhar o status das entregas.

## Padrões de Projeto

### Strategy

A interface `ModalidadeEntrega` define as operações de cálculo
de custo, prazo e identificação da modalidade.

Implementações:
- EntregaConvencional
- EntregaExpressa
- EntregaEconomica

Novas modalidades podem ser adicionadas por meio da implementação
da interface, sem modificar as estratégias existentes.

### Singleton

A classe `CentralLogistica` utiliza Singleton para garantir
uma única instância de gerenciamento dos pedidos durante
a execução da aplicação.

## Regras de cálculo adotadas

Os valores abaixo são parâmetros demonstrativos, pois o enunciado
não determina tarifas ou prazos numéricos.

| Modalidade | Custo | Prazo |
|---|---|---|
| Convencional | R$ 15,00 + R$ 0,80/km | Mínimo 1 dia; 1 dia a cada 300 km |
| Expressa | Custo convencional + R$ 25,00 | Mínimo 1 dia; 1 dia a cada 500 km |
| Econômica | 75% do custo convencional | 3 dias + 1 dia a cada 150 km |

O peso é armazenado no pedido, mas não participa do cálculo,
pois não há regra de cobrança por peso na especificação.

## Estrutura do projeto

```text
src/main/java/
├── Bootstrap.java
└── com/psoft/entregas/
    ├── Main.java
    ├── model/
    │   ├── Pedido.java
    │   └── StatusPedido.java
    ├── service/
    │   └── CentralLogistica.java
    └── strategy/
        ├── ModalidadeEntrega.java
        ├── EntregaConvencional.java
        ├── EntregaExpressa.java
        └── EntregaEconomica.java
```

## Requisitos

- JDK 21 ou superior.

## Como executar

Na raiz do projeto, execute:

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp out Bootstrap
```

O programa demonstra o cadastro de pedidos, os cálculos,
a alteração de modalidade, o despacho e a finalização
de entregas, além da verificação do Singleton.
