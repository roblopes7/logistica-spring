package com.zap.genesis.logistica.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "estado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "seq_estado")
    @SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado", allocationSize = 0, initialValue = 1, schema = "public")
    @Column(name = "idestado")
    private Integer codigo;
    @Column(name = "nome", nullable = false)
    @NotEmpty(message = "Nome do estado obrigatório.")
    @Size(min = 2, max = 50, message = "Nome do estado deve ter entre 2 e 50 caracteres.")
    private String nome;
    @Column(name = "sigla")
    @Size(max = 3)
    private String sigla;
    @Column(name = "pais")
    private String pais;

    @Override
    public String toString() {
        return "Estado{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
