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


    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTeste(){
        Estado estado = gerarEstado();
        Estado estadoGravado = repository.save(estado);

        Assertions.assertThat(estadoGravado.getCodigo()).isNotNull();
    }

    private Estado gerarEstado() {
        return Estado.builder()
                .nome("Paraná")
                .pais("Brasil")
                .sigla("PR")
                .build();
    }


}
