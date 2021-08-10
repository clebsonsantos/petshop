CREATE TABLE IF NOT EXISTS endereco (
                id_endereco BIGINT(20) AUTO_INCREMENT NOT NULL,
                data_cadastro DATETIME NOT NULL,
                numero VARCHAR(10) NOT NULL,
                ponto_referencia VARCHAR(500),
                id_logradouro BIGINT(20),
                id_cidade BIGINT(20),
                PRIMARY KEY (id_endereco),
                FOREIGN KEY (id_logradouro) REFERENCES logradouro(id_logradouro),
                FOREIGN KEY (id_cidade) REFERENCES logradouro(id_cidade)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE pessoa ADD COLUMN id_endereco BIGINT(20) NOT NULL;

ALTER TABLE pessoa ADD CONSTRAINT cliente_endereco_fk
FOREIGN KEY (id_endereco)
REFERENCES endereco (id_endereco);