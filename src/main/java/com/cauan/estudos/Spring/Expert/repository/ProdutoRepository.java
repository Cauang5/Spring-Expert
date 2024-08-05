package com.cauan.estudos.Spring.Expert.repository;

import com.cauan.estudos.Spring.Expert.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
