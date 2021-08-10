CREATE TABLE IF NOT EXISTS prontuario (
                id_prontuario BIGINT(20) AUTO_INCREMENT NOT NULL,
                data_cadastro DATETIME NOT NULL,
                matricula VARCHAR(10) NOT NULL,
                data_ultima_edicao DATETIME,                
                observacao VARCHAR(500),                
                PRIMARY KEY (id_prontuario)                
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE animal ADD COLUMN id_prontuario BIGINT(20) NOT NULL;

ALTER TABLE animal ADD CONSTRAINT animal_prontuario_fk
FOREIGN KEY (id_prontuario)
REFERENCES prontuario (id_prontuario);