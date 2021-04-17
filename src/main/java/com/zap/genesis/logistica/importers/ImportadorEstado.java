package com.zap.genesis.logistica.importers;

import com.zap.genesis.logistica.domain.Estado;

import java.util.ArrayList;
import java.util.List;

public class ImportadorEstado extends ImportadorGenerico{


    List<Estado> estados = new ArrayList<>();

    @Override
    public void lerLinha(String linhaCompleta, int nLinha) throws Exception {

        String[] linha = linhaCompleta.replaceAll("\"", "").split("\\|");

        Estado estado = new Estado();
        estado.setSigla(linha[0]);
        estado.setNome(linha[1]);
        estado.setPais(linha[2]);
        estados.add(estado);
    }

    public List<Estado> getEstados() {
        return estados;
    }
}
