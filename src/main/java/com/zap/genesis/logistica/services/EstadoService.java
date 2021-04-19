package com.zap.genesis.logistica.services;

import com.zap.genesis.logistica.domain.Estado;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public interface EstadoService {
    Estado save(Estado estado);

    Optional<Estado> getById(Integer id);

    Estado update(Estado atualizado);

    List<Estado> findAll();

    void delete(Estado estado);
}
