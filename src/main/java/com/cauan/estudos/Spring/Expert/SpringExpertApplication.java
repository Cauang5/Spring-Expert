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

}
