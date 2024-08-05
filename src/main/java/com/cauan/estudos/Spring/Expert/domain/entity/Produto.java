package com.cauan.estudos.Spring.Expert.domain.entity;

import java.math.BigDecimal;

public class Produto {

    private Integer id;
    private String descricao;
    private BigDecimal preco;

    public Integer id() {
        return id;
    }

    public Produto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String descricao() {
        return descricao;
    }

    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public BigDecimal preco() {
        return preco;
    }

    public Produto setPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }
}
