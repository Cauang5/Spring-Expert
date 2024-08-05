package com.cauan.estudos.Spring.Expert.domain.entity;

public class ItemPedido {

    private Integer id;
    private Pedido pedido;
    private Produto produto;
    private Integer quantidade;

    public Integer id() {
        return id;
    }

    public ItemPedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public Pedido pedido() {
        return pedido;
    }

    public ItemPedido setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public Produto produto() {
        return produto;
    }

    public ItemPedido setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public Integer quantidade() {
        return quantidade;
    }

    public ItemPedido setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }
}
