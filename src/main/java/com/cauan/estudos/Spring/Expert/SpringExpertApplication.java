package com.cauan.estudos.Spring.Expert;

import com.cauan.estudos.Spring.Expert.domain.entity.Cliente;
import com.cauan.estudos.Spring.Expert.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringExpertApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringExpertApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ClienteRepository clienteRepository) {
        return args -> {
            System.out.println("Salvando clientes");
            clienteRepository.save(new Cliente("José"));
            clienteRepository.save(new Cliente("Carlos"));
            clienteRepository.save(new Cliente("Carlos Roberto"));
            clienteRepository.save(new Cliente("Carlos Pereira"));

            List<Cliente> allClientes = clienteRepository.findByNomeLike("%Carlos%");
            allClientes.forEach(cliente -> System.out.println(cliente.nome()));

        };
    }
}
