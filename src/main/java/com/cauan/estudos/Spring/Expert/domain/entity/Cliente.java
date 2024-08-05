package com.cauan.estudos.Spring.Expert.domain.entity;

public class Cliente {

    private Integer id;
    private String nome;

    public Integer id() {
        return id;
    }

    public Cliente setId(Integer id) {
        this.id = id;
        return this;
    }

    public String nome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }
}
