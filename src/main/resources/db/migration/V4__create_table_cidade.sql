create table cidade (
    idcidade integer,
    nome varchar(50) NOT NULL,
    idestado integer,
    cod_ibge integer UNIQUE,
    CONSTRAINT pk_cidade PRIMARY KEY(idcidade),
    CONSTRAINT fk_cidade_estado FOREIGN KEY(idestado)
        REFERENCES estado(idestado)
);

CREATE SEQUENCE seq_cidade
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    OWNED BY cidade.idcidade;