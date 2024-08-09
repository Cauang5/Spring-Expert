package com.cauan.estudos.Spring.Expert.controller;

import com.cauan.estudos.Spring.Expert.domain.entity.Produto;
import com.cauan.estudos.Spring.Expert.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto){
        Produto savedProduto = produtoService.create(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProduto(){
        List<Produto> produtos = produtoService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id){
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteProduto(@PathVariable Long id){
        Optional<Produto> produto =  produtoService.delete(id);
        if (produto.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        Optional<Produto> produto = produtoService.update(id, produtoAtualizado);
        return produto.map(updateProduto -> ResponseEntity.ok(updateProduto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

}
