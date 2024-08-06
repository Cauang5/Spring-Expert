package com.cauan.estudos.Spring.Expert.controller;

import com.cauan.estudos.Spring.Expert.domain.entity.Cliente;
import com.cauan.estudos.Spring.Expert.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {


    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listAll());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.findById(id);
        if (cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Cliente> delete (@PathVariable Long id){
        Optional<Cliente> cliente = clienteService.delete(id);
        if (cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        clienteService.delete(id);
        // Ideal seria utilizar NO_CONTENT (204), porem, ele não deve receber um corpo na requisição
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteAtt = clienteService.update(id, cliente);
        if (clienteAtt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
