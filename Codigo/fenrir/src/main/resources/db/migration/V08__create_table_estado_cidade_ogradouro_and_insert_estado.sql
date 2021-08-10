CREATE TABLE estado (
  id_estado BIGINT(20) NOT NULL,
  descricao varchar(45) NOT NULL,
  uf varchar(2) NOT NULL,
  PRIMARY KEY (id_estado),
  UNIQUE KEY uf_UNIQUE (uf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade (
  id_cidade BIGINT(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(100) DEFAULT NULL,
  id_estado BIGINT(20) NULL,
  codigo_ibge BIGINT(20) DEFAULT NULL,
  ddd varchar(2) DEFAULT NULL,
  PRIMARY KEY (id_cidade),
  FOREIGN KEY (id_estado) REFERENCES estado (id_estado)
  
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE logradouro (
  CEP varchar(25),
  id_logradouro BIGINT(20) AUTO_INCREMENT NOT NULL ,
  tipo varchar(50),
  descricao varchar(100) NOT NULL,
  id_cidade BIGINT(20) NOT NULL,
  UF varchar(2),
  complemento varchar(100),
  descricao_sem_numero varchar(100),
  descricao_cidade varchar(100),
  codigo_cidade_ibge BIGINT(20),
  bairro varchar(100),
  PRIMARY KEY (id_logradouro),  
  FOREIGN KEY (id_cidade) REFERENCES cidade (id_cidade)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	

INSERT INTO estado VALUES(1, 'Acre', 'AC'),
(2, 'Alagoas', 'AL'),
(3, 'Amazonas', 'AM'),
(4, 'Amapá', 'AP'),
(5, 'Bahia', 'BA'),
(6, 'Ceará', 'CE'),
(7, 'Distrito Federal', 'DF'),
(8, 'Espírito Santo', 'ES'),
(9, 'Goiás', 'GO'),
(10, 'Maranhão', 'MA'),
(11, 'Minas Gerais', 'MG'),
(12, 'Mato Grosso do Sul', 'MS'),
(13, 'Mato Grosso', 'MT'),
(14, 'Pará', 'PA'),
(15, 'Paraíba', 'PB'),
(16, 'Pernambuco', 'PE'),
(17, 'Piauí', 'PI'),
(18, 'Paraná', 'PR'),
(19, 'Rio de Janeiro', 'RJ'),
(20, 'Rio Grande do Norte', 'RN'),
(21, 'Rondônia', 'RO'),
(22, 'Roraima', 'RR'),
(23, 'Rio Grande do Sul', 'RS'),
(24, 'Santa Catarina', 'SC'),
(25, 'Sergipe', 'SE'),
(26, 'São Paulo', 'SP'),
(27, 'Tocantins', 'TO');


