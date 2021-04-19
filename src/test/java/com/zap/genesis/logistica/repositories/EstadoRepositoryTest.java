package com.zap.genesis.logistica.repositories;

import com.zap.genesis.logistica.domain.Estado;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EstadoRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EstadoRepository repository;


    //POST

    @Test
    @DisplayName("Deve salvar um estado")
    public void salvarEstadoTeste(){
        Estado estado = gerarEstado();
        Estado estadoGravado = repository.save(estado);

        Assertions.assertThat(estadoGravado.getCodigo()).isNotNull();
    }


    //GET

    @Test
    @DisplayName("Deve obter um estado por ID")
    public void filtrarPorIDTeste(){
        Estado estado = gerarEstado();
        entityManager.persist(estado);
        Optional<Estado> estadoencontrado = repository.findById(estado.getCodigo());
        Assertions.assertThat(estadoencontrado.isPresent()).isTrue();

    }


    //UPDATE

    @Test
    @DisplayName("Deve editar um estado")
    public void ediatarEstadoTeste(){
        Estado estado = gerarEstado();
        entityManager.persist(estado);

        Estado estadoAtualizado = Estado.builder().codigo(estado.getCodigo()).nome("California").pais("Estados Unidos").sigla("CA").build();

        Estado estadoRetornado = repository.save(estadoAtualizado);

        Assertions.assertThat(estadoRetornado.getCodigo()).isEqualTo(estado.getCodigo());
        Assertions.assertThat(estadoRetornado.getSigla()).isEqualTo(estado.getSigla());
        Assertions.assertThat(estadoRetornado.getPais()).isEqualTo(estado.getPais());
        Assertions.assertThat(estadoRetornado.getNome()).isEqualTo(estado.getNome());
    }

    //DELETE

    @Test
    @DisplayName("Deve deletar um estado")
    public void deletarEstadoTest(){
        Estado estado = gerarEstado();
        entityManager.persist(estado);

        Estado estadoEncontrado = entityManager.find(Estado.class, estado.getCodigo());
        repository.delete(estadoEncontrado);
        Estado estadoDeletado = entityManager.find(Estado.class, estado.getCodigo());
        Assertions.assertThat(estadoDeletado).isNull();
    }


    //UTILS
    private Estado gerarEstado() {
        return Estado.builder()
                .nome("Paraná")
                .pais("Brasil")
                .sigla("PR")
                .build();
    }


}
