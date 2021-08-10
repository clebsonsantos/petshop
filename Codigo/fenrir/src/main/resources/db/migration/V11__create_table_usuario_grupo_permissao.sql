CREATE TABLE IF NOT EXISTS usuario (
                id_usuario BIGINT(20) AUTO_INCREMENT NOT NULL,
                login VARCHAR(20) NOT NULL,
                senha VARCHAR(500) NOT NULL,
                data_cadastro DATETIME NOT NULL,
                data_alteracao DATETIME NOT NULL,
                data_acesso DATETIME,
                ativo BOOLEAN DEFAULT true,
                id_funcionario BIGINT(20) NOT NULL,
                PRIMARY KEY (id_usuario),
                FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS grupo (
                id_grupo BIGINT(20) AUTO_INCREMENT NOT NULL,
                descricao VARCHAR(50) NOT NULL,
                observacao VARCHAR(100),                
                PRIMARY KEY (id_grupo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS permissao (
                id_permissao BIGINT(20) AUTO_INCREMENT NOT NULL,
                descricao VARCHAR(50) NOT NULL,
                observacao VARCHAR(100),
                PRIMARY KEY (id_permissao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS usuario_grupo (
                id_usuario BIGINT(20)  NOT NULL,
                id_grupo BIGINT(20)  NOT NULL,               
                PRIMARY KEY (id_usuario, id_grupo),
                FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
                FOREIGN KEY (id_grupo) REFERENCES grupo (id_grupo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS grupo_permissao (
                id_grupo BIGINT(20)  NOT NULL,
                id_permissao BIGINT(20)  NOT NULL,               
                PRIMARY KEY (id_grupo,id_permissao),                
                FOREIGN KEY (id_grupo) REFERENCES grupo (id_grupo),
                FOREIGN KEY (id_permissao) REFERENCES permissao (id_permissao)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




