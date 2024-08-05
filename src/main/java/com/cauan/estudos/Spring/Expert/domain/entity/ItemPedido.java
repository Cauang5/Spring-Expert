package com.cauan.estudos.Spring.Expert.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_ITEMPEDIDO")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Long id() {
        return id;
    }

    public ItemPedido setId(Long id) {
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
