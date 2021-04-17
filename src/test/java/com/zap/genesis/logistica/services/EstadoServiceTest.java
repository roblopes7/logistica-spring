package com.zap.genesis.logistica.services;

import com.zap.genesis.logistica.domain.Estado;
import com.zap.genesis.logistica.repositories.EstadoRepository;
import com.zap.genesis.logistica.services.imp.EstadoServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class EstadoServiceTest {


    EstadoService service;

    @MockBean
    EstadoRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new EstadoServiceImp(repository);
    }

    //POST

    @Test
    @DisplayName("Deve salvar um estado.")
    public void salvarEstadoTeste() {
        Estado estado = gerarEstadoValido();
        Mockito.when(repository.save(estado)).thenReturn(Estado.builder().codigo(1).nome("EstadoTeste").pais("Brasil-Teste").sigla("TBR").build());
        Estado estadoGravado = service.save(estado);

        Assertions.assertThat(estadoGravado.getCodigo()).isNotNull();
        Assertions.assertThat(estadoGravado.getNome()).isEqualTo("EstadoTeste");
        Assertions.assertThat(estadoGravado.getSigla()).isEqualTo("TBR");
        Assertions.assertThat(estadoGravado.getPais()).isEqualTo("Brasil-Teste");
    }

    //GET

    @Test
    @DisplayName("Deve retornar um estado pelo id")
    public void consultarEstadoTeste(){
        Integer id = 1;
        Estado estado = gerarEstadoValido();
        estado.setCodigo(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(estado));

        Optional<Estado> estadoEncontrado = service.getById(id);
        assertThat(estadoEncontrado.isPresent()).isTrue();
        assertThat(estadoEncontrado.get().getCodigo()).isEqualTo(id);
        assertThat(estadoEncontrado.get().getSigla()).isEqualTo(estado.getSigla());
        assertThat(estadoEncontrado.get().getNome()).isEqualTo(estado.getNome());
        assertThat(estadoEncontrado.get().getPais()).isEqualTo(estado.getPais());
    }

    @Test
    @DisplayName("Não deve achar um estado por id")
    public void notFoundBookByIdTest() {
        Integer id = 1;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Estado> estadoEncontrado = service.getById(id);
        assertThat(estadoEncontrado.isPresent()).isFalse();
    }

    private Estado gerarEstadoValido() {
        return Estado.builder().nome("EstadoTeste").pais("Barsil-Teste").sigla("TBR").build();
    }


}
