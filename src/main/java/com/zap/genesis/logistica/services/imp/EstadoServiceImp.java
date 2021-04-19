package com.zap.genesis.logistica.services.imp;

import com.zap.genesis.logistica.domain.Estado;
import com.zap.genesis.logistica.repositories.EstadoRepository;
import com.zap.genesis.logistica.services.EstadoService;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Estado update(Estado estado) {
        if(estado == null || estado.getCodigo() == null){
            throw new IllegalArgumentException("Estado não cadastrado anteriormente.");
        }
        return estadoRepository.save(estado);

    }

    @Override
    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }


}
