package com.cauan.estudos.Spring.Expert.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {

    private Long cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}
