package com.zap.genesis.logistica;

import com.zap.genesis.logistica.domain.Estado;
import com.zap.genesis.logistica.importers.ImportadorEstado;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@SpringBootApplication
public class LogisticaApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(LogisticaApplication.class, args);
	}

}
