package com.cauan.estudos.Spring.Expert.controller;

import com.cauan.estudos.Spring.Expert.DTO.PedidoDTO;
import com.cauan.estudos.Spring.Expert.domain.entity.Pedido;
import com.cauan.estudos.Spring.Expert.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long criar (@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.criarPedido(dto);
        return pedido.getId();
    }


}
