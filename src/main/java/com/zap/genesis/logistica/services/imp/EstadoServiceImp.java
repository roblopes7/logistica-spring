package com.zap.genesis.logistica.services.imp;

import com.zap.genesis.logistica.domain.Estado;
import com.zap.genesis.logistica.repositories.EstadoRepository;
import com.zap.genesis.logistica.services.EstadoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoServiceImp implements EstadoService {


    final  EstadoRepository estadoRepository;

    public EstadoServiceImp(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Override
    public Optional<Estado> getById(Integer id) {
        return estadoRepository.findById(id);
    }
}
