CREATE TABLE IF NOT EXISTS funcionario (
                id_pessoa BIGINT(20) NOT NULL,
                cpf VARCHAR(20) NOT NULL,
                especialidade VARCHAR(500),
                crmv VARCHAR(250),
                matricula VARCHAR(20) NOT NULL,
                salario NUMERIC(10,2),
                data_admissao DATETIME NOT NULL,
                PRIMARY KEY (id_pessoa),
                FOREIGN KEY (id_pessoa) REFERENCES pessoa (id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;