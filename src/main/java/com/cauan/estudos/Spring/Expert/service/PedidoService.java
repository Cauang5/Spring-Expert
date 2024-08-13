package com.cauan.estudos.Spring.Expert.service;

import ch.qos.logback.core.net.server.Client;
import com.cauan.estudos.Spring.Expert.DTO.ItemPedidoDTO;
import com.cauan.estudos.Spring.Expert.DTO.PedidoDTO;
import com.cauan.estudos.Spring.Expert.Exception.PedidoNaoEncontradoException;
import com.cauan.estudos.Spring.Expert.domain.entity.Cliente;
import com.cauan.estudos.Spring.Expert.domain.entity.ItemPedido;
import com.cauan.estudos.Spring.Expert.domain.entity.Pedido;
import com.cauan.estudos.Spring.Expert.domain.entity.Produto;
import com.cauan.estudos.Spring.Expert.repository.ClienteRepository;
import com.cauan.estudos.Spring.Expert.repository.ItemPedidoRepository;
import com.cauan.estudos.Spring.Expert.repository.PedidoRepository;
import com.cauan.estudos.Spring.Expert.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor//Gera o construtor dos argumentos obrigatórios (final)
@Transactional //Caso de erro, irá dar rollback
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;


    public Pedido criarPedido(PedidoDTO dto){
        var cliente = clienteRepository
                .findById(dto.getCliente())
                .orElseThrow(() -> new PedidoNaoEncontradoException("Código do cliente inválido"));

        var pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        pedido.setItens(itensPedido);

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);

        return pedido;
    }

    public PedidoDTO findByID(Long id){
        var pedido = pedidoRepository
                .findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado pelo ID: " +id));

        return converterParaDTO(pedido);
    }

    public PedidoDTO atualizarPedido(Long id, PedidoDTO dto){
        var pedidoExistente = pedidoRepository
                .findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado pelo ID: " +id));

        var cliente = clienteRepository
                .findById(dto.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado pelo ID: " + dto.getCliente()));

        pedidoExistente.setTotal(dto.getTotal());
        pedidoExistente.setItens(converterItens(pedidoExistente, dto.getItens()));

        pedidoRepository.save(pedidoExistente);

        return converterParaDTO(pedidoExistente);
    }

    public void deletarPedido(Long id){
        Pedido pedido = pedidoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedidoRepository.delete(pedido);
    }

    //Método para converter um Pedido em PedidoDTO (Por segurança)
    private PedidoDTO converterParaDTO(Pedido pedido) {
        List<ItemPedidoDTO> itensDTO = pedido.getItens()
                .stream()
                .map(this::converterItemParaDTO)
                .collect(Collectors.toList());

        PedidoDTO dto = new PedidoDTO();
        dto.setCliente(pedido.getCliente().getId());
        dto.setTotal(pedido.getTotal());
        dto.setItens(itensDTO);

        return dto;
    }

    //Método para converter um ItemPedido para um ItemPedido DTO
    private ItemPedidoDTO converterItemParaDTO(ItemPedido item){
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setProduto(item.getProduto().getId());
        dto.setQuantidade(item.getQuantidade());

        return dto;
    }

    //Método para converter uma lista de ItemPedido para uma lista de itemPedido
    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new RuntimeException("Não é possível realizar um pedido sem item");
        }

        return itens
                .stream()
                .map(dto -> {
                    Long idProduto = dto.getProduto();
                    Produto produto = produtoRepository
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
