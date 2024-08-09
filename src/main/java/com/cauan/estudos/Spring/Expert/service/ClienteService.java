package com.cauan.estudos.Spring.Expert.service;

import com.cauan.estudos.Spring.Expert.domain.entity.Cliente;
import com.cauan.estudos.Spring.Expert.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente create(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public List<Cliente> listAll() {
        Sort sort = Sort.by("nome").ascending();
        return clienteRepository.findAll(sort);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> update(Long id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(clienteExiste -> {
                    clienteExiste.setNome(cliente.getNome());
                    return clienteRepository.save(clienteExiste);
                });
    }

    public Optional<Cliente> delete(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.deleteById(id);
            return cliente;
        } else {
            return Optional.empty();
        }
    }

   /* @Transactional
    public Cliente findClienteFetchPedidos(Long id) {
        return clienteRepository.findClienteFetchPedidos(id);
    }
*/
}
