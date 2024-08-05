package com.cauan.estudos.Spring.Expert.repository;

import com.cauan.estudos.Spring.Expert.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);
}
