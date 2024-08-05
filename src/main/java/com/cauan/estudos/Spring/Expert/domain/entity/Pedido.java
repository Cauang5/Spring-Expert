package com.cauan.estudos.Spring.Expert.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataPedido;
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    public Long id() {
        return id;
    }

    public Pedido setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate dataPedido() {
        return dataPedido;
    }

    public Pedido setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }

    public BigDecimal total() {
        return total;
    }

    public Pedido setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }
}
