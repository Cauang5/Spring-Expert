package com.cauan.estudos.Spring.Expert.repository;

import com.cauan.estudos.Spring.Expert.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Long id);
}
