package com.zap.genesis.logistica.utils;

import java.io.*;

public class FileUtils {

    public static Integer getNumeroDeLinhas(File arquivoLeitura) {
        int qtdLinha = 0;
        try (LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura))) {
            linhaLeitura.skip(arquivoLeitura.length());
            qtdLinha = linhaLeitura.getLineNumber();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qtdLinha;
    }
}
