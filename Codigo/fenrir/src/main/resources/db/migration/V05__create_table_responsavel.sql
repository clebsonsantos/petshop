CREATE TABLE  responsavel (
                id_responsavel BIGINT(20) AUTO_INCREMENT NOT NULL,
                nome VARCHAR(500),
                observacao VARCHAR(500),
                id_cliente BIGINT(20) NOT NULL,
                PRIMARY KEY (id_responsavel),                
                FOREIGN KEY (id_cliente) REFERENCES cliente (id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
