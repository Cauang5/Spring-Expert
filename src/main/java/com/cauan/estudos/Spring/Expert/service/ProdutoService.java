package com.cauan.estudos.Spring.Expert.service;

import com.cauan.estudos.Spring.Expert.domain.entity.Produto;
import com.cauan.estudos.Spring.Expert.repository.ProdutoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto create(Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    public List<Produto> listAll() {
        Sort sort = Sort.by("descricao").ascending();
        return produtoRepository.findAll(sort);
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> update(Long id, Produto produto) {
        return produtoRepository.findById(id)
                .map(produtoExiste -> {
                    produtoExiste.setDescricao(produto.getDescricao());
                    produtoExiste.setPreco(produto.getPreco());
                    return produtoRepository.save(produtoExiste);
                });
    }

    public Optional<Produto> delete(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.deleteById(id);
            return produto;
        } else {
            return Optional.empty();
        }
    }
}
