CREATE TABLE estado(
        idestado integer,
        nome varchar(50) NOT NULL,
        CONSTRAINT pk_estado PRIMARY KEY(idestado)
);

CREATE SEQUENCE seq_estado
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    OWNED BY estado.idestado;