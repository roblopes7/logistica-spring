package com.zap.genesis.logistica.resources;

import com.zap.genesis.logistica.domain.Estado;
import com.zap.genesis.logistica.services.EstadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    EstadoService service;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salvar(@RequestBody @Valid Estado estado) {
        return service.save(estado);
    }

    @GetMapping("{id}")
    public Estado visualizar(@PathVariable Integer id){
        return service
                .getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public Estado atualizar(@PathVariable Integer id,@RequestBody @Valid Estado atualizado) {
        return  service.getById(id).map(estado -> {
            estado.setSigla(atualizado.getSigla());
            estado.setPais(atualizado.getPais());
            estado.setNome(atualizado.getNome());
            estado = service.update(estado);
            return modelMapper.map(estado, Estado.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));    }

    @GetMapping()
    public List<Estado> retornarTodosEstados(){
        return service.findAll();
    }



}
