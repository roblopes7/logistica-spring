package com.zap.genesis.logistica.repositories;

import com.zap.genesis.logistica.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    Optional<Estado> findByNome(Integer id);
}
