package com.cauan.estudos.Spring.Expert.domain.entity;

import com.cauan.estudos.Spring.Expert.repository.ClienteRepository;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos; //Retornar os pedidos do cliente

    public Cliente(){

    }

    public Set<Pedido> pedidos() {
        return pedidos;
    }

    public Cliente setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
        return this;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Long id() {
        return id;
    }

    public Cliente setId(Long id) {
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
