package com.zap.genesis.logistica.importers;

import com.zap.genesis.logistica.utils.FileUtils;

import java.io.*;

public abstract class ImportadorGenerico {

    public int qtdLinhas = 0;

    public boolean importar(File file, String separador) {
        int nLinha = 0;

        try(BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "Cp1252"))){

            //setUp()
            setQtdLinhas(FileUtils.getNumeroDeLinhas(file));
            while(in.ready()) {
                String linha = in.readLine();
                lerLinha(linha, ++nLinha);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO AO IMPORTAR!!");
        }
        return true;
    }

    public abstract void lerLinha(String linha, int nLinha) throws Exception;


    public int getQtdLinhas() {
        return qtdLinhas;
    }

    public void setQtdLinhas(int qtdLinhas) {
        this.qtdLinhas = qtdLinhas;
    }

}
