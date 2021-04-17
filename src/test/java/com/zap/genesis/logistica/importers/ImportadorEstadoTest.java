package com.zap.genesis.logistica.importers;

import com.zap.genesis.logistica.domain.Estado;
import com.zap.genesis.logistica.repositories.EstadoRepository;
import com.zap.genesis.logistica.services.EstadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

public class ImportadorEstadoTest {

    @Autowired
    EstadoService service;

    final String PATH = "C:\\Desenvolvimento\\Zap\\logistica\\src\\test\\java\\com\\zap\\genesis\\logistica\\importers\\files\\estados";


    @Test
    @DisplayName("Importar e listar no console os estados")
    public void importarListaDeEstadosArquivoExternoWKRadarTeste(){
        File file = new File(PATH);
        ImportadorEstado importadorEstado = new ImportadorEstado();
        importadorEstado.importar(file, "|");
        List<Estado> estados = importadorEstado.getEstados();

        for(Estado i : estados) {
            System.out.println(i);
        }
    }

    @Test
    @DisplayName("Importar e tentas salvar a lista de estados")
    public void importarSalvarListaDeEstadosArquivoExternoWKRadarTeste(){
        File file = new File(PATH);
        ImportadorEstado importadorEstado = new ImportadorEstado();
        importadorEstado.importar(file, "|");
        List<Estado> estados = importadorEstado.getEstados();

        for(Estado estado : estados) {
            service.save(estado);
        }
    }
}
