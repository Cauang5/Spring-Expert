package com.cauan.estudos.Spring.Expert.service;

import com.cauan.estudos.Spring.Expert.DTO.ItemPedidoDTO;
import com.cauan.estudos.Spring.Expert.DTO.PedidoDTO;
import com.cauan.estudos.Spring.Expert.domain.entity.ItemPedido;
import com.cauan.estudos.Spring.Expert.domain.entity.Pedido;
import com.cauan.estudos.Spring.Expert.repository.ClienteRepository;
import com.cauan.estudos.Spring.Expert.repository.ItemPedidoRepository;
import com.cauan.estudos.Spring.Expert.repository.PedidoRepository;
import com.cauan.estudos.Spring.Expert.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //Gera o construtor dos argumentos obrigatórios (final)
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public Pedido criarPedido(PedidoDTO dto){
        System.out.println("Funcionando");
        Long idCliente = dto.getCliente();
        System.out.println("Funcionando" +idCliente);
        var cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Código de cliente inválido")); //Teste de erro

        var pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);
        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new RuntimeException("Não é possível realizar um pedido sem item");
        }

        return itens
                .stream()
                .map(dto -> {
                    Long idProduto = dto.getProduto();
                    var produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RuntimeException("Código de produto inválido" +idProduto));

                    var itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
