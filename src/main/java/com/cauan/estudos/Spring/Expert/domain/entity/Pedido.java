package com.cauan.estudos.Spring.Expert.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Pedido {

    private Integer id;
    private Cliente cliente;
    private LocalDate dataPedido;
    private BigDecimal total;

    public Integer id() {
        return id;
    }

    public Pedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public Cliente cliente() {
        return cliente;
    }

    public Pedido setCliente(Cliente cliente) {
        this.cliente = cliente;
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
