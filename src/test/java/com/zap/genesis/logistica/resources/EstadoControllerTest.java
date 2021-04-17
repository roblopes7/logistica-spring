package com.zap.genesis.logistica.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zap.genesis.logistica.domain.Estado;
import com.zap.genesis.logistica.services.EstadoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = EstadoController.class)
@AutoConfigureMockMvc
public class EstadoControllerTest {

    static String ESTADOS_API = "/api/estados";

    @Autowired
    MockMvc mvc;

    @MockBean
    EstadoService service;

    //TESTES POST

    @Test
    @DisplayName("Adicionar um estado com sucesso")
    public void salvarEstadoTeste() throws Exception {
        Estado estado = gerarEstadoValido();
        Estado estadoSalvo = gerarEstadoValido();
        estadoSalvo.setCodigo(1);

        BDDMockito.given(service.save(Mockito.any(Estado.class))).willReturn(estadoSalvo);

        String json = new ObjectMapper().writeValueAsString(estado);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ESTADOS_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("codigo").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("sigla").value(estado.getSigla()))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(estado.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("pais").value(estado.getPais()));
    }

    @Test
    @DisplayName("Erro ao tentar salvar estado sem nome")
    public void salvarEstadoSemNomeTeste() throws Exception{
        String json = new ObjectMapper().writeValueAsString(new Estado());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ESTADOS_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(1)));

    }

    @Test
    @DisplayName("Erro ao tentar salvar estado sigla com mais de 3 caracteres")
    public void salvarEstadoSiglaMaiorTeste() throws Exception{
        Estado estado = new Estado();
        estado.setSigla("ABC123");
        String json = new ObjectMapper().writeValueAsString(estado);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ESTADOS_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);


        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("errors", Matchers.hasSize(2))); // 2 Erros: nome vazio E sigla maior que 3 chars
    }

    //TESTES GET

    @Test
    @DisplayName("Consultar estado pelo id")
    public void consultarEstadoTeste() throws Exception{
        Integer id = 1;
        Estado estado = gerarEstadoValido();
        estado.setCodigo(1);
        BDDMockito.given(service.getById(id)).willReturn(Optional.of(estado));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(ESTADOS_API.concat("/" + id))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("codigo").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(estado.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("sigla").value(estado.getSigla()))
                .andExpect(MockMvcResultMatchers.jsonPath("pais").value(estado.getPais()));

    }

    @Test
    @DisplayName("Consultar estado inexistente")
    public void erroAoConsultarEstadoInexistenteTeste() throws Exception{
        BDDMockito.given(service.getById(Mockito.anyInt())).willReturn(Optional.empty());
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(ESTADOS_API + "/" + 1)
                .accept(MediaType.APPLICATION_JSON);

    }


    //MÉTODOS UPDATE




    //MÉTODOS UTILS

    private Estado gerarEstadoValido() {
        return Estado.builder().nome("EstadoTeste").pais("Barsil-Teste").sigla("TBR").build();
    }
}
