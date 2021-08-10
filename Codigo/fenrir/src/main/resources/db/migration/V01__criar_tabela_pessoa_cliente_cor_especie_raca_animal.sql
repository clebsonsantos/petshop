CREATE TABLE  pessoa (
                id_pessoa BIGINT(20)  AUTO_INCREMENT NOT NULL,
                nome VARCHAR(500) NOT NULL,
                rg VARCHAR(20),
                sexo CHAR,
                data_nascimento DATE,
                telefone_celular VARCHAR(20),
                telefone_fixo VARCHAR(20),
                email VARCHAR(250),
                observacao VARCHAR(1000),
                data_cadastro DATETIME NOT NULL,
                PRIMARY KEY (id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  cliente (
                id_pessoa BIGINT(20) NOT NULL,
                cpf VARCHAR(20),
                cnpj VARCHAR(20),
                tipo_pessoa VARCHAR(50) NOT NULL,
                is_vip BOOLEAN DEFAULT false NOT NULL,
                PRIMARY KEY (id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  cor (
                id_cor BIGINT(20) AUTO_INCREMENT NOT NULL,
                descricao VARCHAR(250) NOT NULL,
                data_cadastro DATETIME NOT NULL,
                PRIMARY KEY (id_cor)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  especie (
                id_especie BIGINT(20) AUTO_INCREMENT NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                PRIMARY KEY (id_especie)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  raca (
                id_raca BIGINT(20) AUTO_INCREMENT NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                id_especie BIGINT(20) NOT NULL,
                PRIMARY KEY (id_raca),
                FOREIGN KEY (id_especie) REFERENCES especie(id_especie)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE animal (
                id_animal BIGINT(20) AUTO_INCREMENT NOT NULL,
                nome VARCHAR(500) NOT NULL,
                pelagem VARCHAR(200) NOT NULL,
                sexo VARCHAR(1) NOT NULL,
                peso DECIMAL(10,2) NOT NULL,
                data_nascimento DATE NOT NULL,
                imagem VARCHAR(100) NULL,
                content_type VARCHAR(100) NULL,
                status_animal BOOLEAN NOT NULL,
                id_cliente BIGINT(20) NOT NULL,
                id_raca BIGINT(20) NOT NULL,
                id_cor BIGINT(20) NOT NULL,
                PRIMARY KEY (id_animal),
                FOREIGN KEY (id_cor) REFERENCES cor(id_cor),
                FOREIGN KEY (id_raca) REFERENCES raca(id_raca),
                FOREIGN KEY (id_cliente) REFERENCES cliente (id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cor VALUES
	(0,'Amarelo', NOW()), 
	(0,'Chocolate', NOW()), 
	(0,'Branco', NOW()), 
	(0,'Preto', NOW()), 
	(0,'Cinzento', NOW()), 
	(0,'Dourado', NOW()), 
	(0,'Creme', NOW()),
	(0,'Azul', NOW()),	
	(0,'Fulvo Branco', NOW()), 
	(0,'Tigrado', NOW()), 
	(0,'Merle', NOW()), 
	(0,'Arlequim', NOW()), 
	(0,'Lobeiro', NOW()), 
	(0,'Sal e Pimenta', NOW()), 
	(0,'Ruão', NOW()), 
	(0,'Pintaigado', NOW()),
	(0,'Albino', NOW()),	
	(0,'Baio ', NOW()),
	(0,'Bicolor', NOW()),
	(0,'Black and Tan', NOW()),
	(0,'Brown and Tan', NOW()),
	(0,'Despigmentado', NOW()),
	(0,'Mosqueado', NOW()),
	(0,'Particolor', NOW()),
	(0,'Sólido', NOW()),
	(0,'Tricolor', NOW()),
	(0,'Malhado', NOW());
	
