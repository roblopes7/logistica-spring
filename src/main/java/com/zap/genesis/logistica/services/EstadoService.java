package com.zap.genesis.logistica.services;

import com.zap.genesis.logistica.domain.Estado;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public interface EstadoService {
    Estado save(Estado estado);

    Optional<Estado> getById(Integer id);
}
