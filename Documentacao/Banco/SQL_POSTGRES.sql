BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS permissao (
                id_permissao BIGINT NOT NULL,
                descricao VARCHAR(50) NOT NULL,
                observacao VARCHAR(50),
                PRIMARY KEY (id_permissao)
);
CREATE SEQUENCE permissao_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE permissao ALTER COLUMN id_permissao SET DEFAULT NEXTVAL('permissao_id_seq'::regclass);


CREATE TABLE IF NOT EXISTS cor (
                id_cor BIGINT NOT NULL,
                descricao VARCHAR(250) NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW() NOT NULL,
                PRIMARY KEY (id_cor)
);
CREATE SEQUENCE cor_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE cor ALTER COLUMN id_cor SET DEFAULT NEXTVAL('cor_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS tipo_exame (
                id_tipo_exame BIGINT NOT NULL,
                descricao VARCHAR(1000) NOT NULL,
                PRIMARY KEY (id_tipo_exame)
) ;
CREATE SEQUENCE tipo_exame_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE tipo_exame ALTER COLUMN id_tipo_exame SET DEFAULT NEXTVAL('tipo_exame_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS medicamento (
                id_medicamento BIGINT NOT NULL,
                descricao VARCHAR(1000) NOT NULL,
                tipo VARCHAR(1000) NOT NULL,
                quantidade_estoque BIGINT NOT NULL,
                unidade_medida VARCHAR(250) NOT NULL,
                valor DECIMAL(10,2)  NOT NULL,
                PRIMARY KEY (id_medicamento)
) ;
CREATE SEQUENCE medicamento_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE medicamento ALTER COLUMN id_medicamento SET DEFAULT NEXTVAL('medicamento_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS produto (
                id_produto BIGINT NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                unidade_medida VARCHAR(250) NOT NULL,
                quantidade_estoque BIGINT NOT NULL,
                valor DECIMAL(10,2)  NOT NULL,
                PRIMARY KEY (id_produto)
);
CREATE SEQUENCE produto_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE produto ALTER COLUMN id_produto SET DEFAULT NEXTVAL('produto_id_seq'::regclass);


CREATE TABLE IF NOT EXISTS prontuario (
                id_prontuario BIGINT NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                data_ultima_edicao TIMESTAMP NOT NULL,
                matricula VARCHAR(10) NOT NULL,
                observacao VARCHAR(500) NOT NULL,
                PRIMARY KEY (id_prontuario)
) ;
CREATE SEQUENCE prontuario_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE prontuario ALTER COLUMN id_prontuario SET DEFAULT NEXTVAL('prontuario_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS especie (
                id_especie BIGINT NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                PRIMARY KEY (id_especie)
);
CREATE SEQUENCE especie_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE especie ALTER COLUMN id_especie SET DEFAULT NEXTVAL('especie_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS vacina (
                id_vacina BIGINT NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                valor DECIMAL(10,2)  NOT NULL,                
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                PRIMARY KEY (id_vacina)               
);
CREATE SEQUENCE vacina_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE vacina ALTER COLUMN id_vacina SET DEFAULT NEXTVAL('vacina_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS especie_vacina (                
                id_especie BIGINT NOT NULL,
                id_vacina BIGINT NOT NULL,                
                PRIMARY KEY (id_especie,id_vacina),
                FOREIGN KEY (id_especie) REFERENCES especie (id_especie),
                FOREIGN KEY (id_vacina) REFERENCES vacina (id_vacina)
);

CREATE TABLE  IF NOT EXISTS raca (
                id_raca BIGINT NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                id_especie BIGINT NOT NULL,                
                PRIMARY KEY (id_raca),
                FOREIGN KEY (id_especie) REFERENCES especie(id_especie)
);
CREATE SEQUENCE raca_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE raca ALTER COLUMN id_raca SET DEFAULT NEXTVAL('raca_id_seq'::regclass);


CREATE TABLE  IF NOT EXISTS grupo (
                id_grupo BIGINT NOT NULL,
                descricao VARCHAR(50) NOT NULL,
                observacao VARCHAR(100),
                PRIMARY KEY (id_grupo)
);
CREATE SEQUENCE grupo_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE grupo ALTER COLUMN id_grupo SET DEFAULT NEXTVAL('grupo_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS grupo_permissao (                
                id_grupo BIGINT NOT NULL,
                id_permissao BIGINT NOT NULL,
                PRIMARY KEY (id_grupo,id_permissao),
                FOREIGN KEY (id_grupo) REFERENCES grupo(id_grupo),
                FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao)
);

CREATE TABLE IF NOT EXISTS estado (
                id_estado BIGINT NOT NULL,
                descricao varchar(45) NOT NULL,
                uf varchar(2) NOT NULL ,
                PRIMARY KEY (id_estado)
                
);
ALTER TABLE estado ADD CONSTRAINT uf_uq UNIQUE (uf);

CREATE TABLE IF NOT EXISTS cidade (
                id_cidade BIGINT NOT NULL,
                descricao varchar(100) DEFAULT NULL,
                id_estado BIGINT NULL,
                codigo_ibge BIGINT DEFAULT NULL,
                ddd varchar(2) DEFAULT NULL,
                PRIMARY KEY (id_cidade),
                FOREIGN KEY (id_estado) REFERENCES estado (id_estado)  
);


CREATE TABLE IF NOT EXISTS logradouro (
                id_logradouro BIGINT NOT NULL,
                cep varchar(25),                
                tipo varchar(50),
                descricao varchar(100) NOT NULL,
                id_cidade BIGINT NOT NULL,
                uf varchar(2),
                complemento varchar(100),
                descricao_sem_numero varchar(100),
                descricao_cidade varchar(100),
                codigo_cidade_ibge BIGINT,
                bairro varchar(100),
                PRIMARY KEY (id_logradouro),  
                FOREIGN KEY (id_cidade) REFERENCES cidade (id_cidade)
);

CREATE TABLE IF NOT EXISTS endereco (
                id_endereco BIGINT NOT NULL,
                data_cadastro TIMESTAMP NOT NULL,
                numero VARCHAR(10) NOT NULL,
                ponto_referencia VARCHAR(500),
                logradouro_atual VARCHAR(500),
                bairro VARCHAR(500),
                id_logradouro BIGINT,
                id_cidade BIGINT,
                PRIMARY KEY (id_endereco),
                FOREIGN KEY (id_logradouro) REFERENCES logradouro(id_logradouro),
                FOREIGN KEY (id_cidade) REFERENCES cidade(id_cidade)
);
CREATE SEQUENCE endereco_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE endereco ALTER COLUMN id_endereco SET DEFAULT NEXTVAL('endereco_id_seq'::regclass);


CREATE TABLE IF NOT EXISTS pessoa (
                id_pessoa BIGINT NOT NULL,
                nome VARCHAR(500) NOT NULL,
                rg VARCHAR(20),
                sexo CHAR,
                data_nascimento DATE,
                telefone_principal VARCHAR(20) NOT NULL,
                telefone_auxiliar VARCHAR(20),
                email VARCHAR(250),
                observacao VARCHAR(1000),
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                id_endereco BIGINT NOT NULL,
                PRIMARY KEY (id_pessoa),
                FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco)
) ;
CREATE SEQUENCE pessoa_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE pessoa ALTER COLUMN id_pessoa SET DEFAULT NEXTVAL('pessoa_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS funcionario (
                id_pessoa BIGINT NOT NULL,
                cpf VARCHAR(20) NOT NULL,
                especialidade VARCHAR(500),
                crmv VARCHAR(250),
                tipo_funcionario VARCHAR(11) CHECK (tipo_funcionario in ('VETERINARIO', 'ATENDENTE')),
                matricula VARCHAR(20) NOT NULL,
                salario NUMERIC(10,2),
                data_admissao TIMESTAMP NOT NULL,
                PRIMARY KEY (id_pessoa),
                FOREIGN KEY (id_pessoa) REFERENCES pessoa (id_pessoa)
);
CREATE SEQUENCE funcionario_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE funcionario ALTER COLUMN id_pessoa SET DEFAULT NEXTVAL('funcionario_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS  consulta (
                id_consulta BIGINT NOT NULL,
                id_prontuario BIGINT NOT NULL,
                data_consulta DATE NOT NULL,
                hora_consulta TIME  NOT NULL,                
                situacao  VARCHAR(45) CHECK (situacao in ('ATENDIDO','CONFIRMADO','PENDENTE','CANCELADO','FINALIZADO')),
                is_pago BOOLEAN NOT NULL DEFAULT false,
                is_cancelado BOOLEAN NOT NULL DEFAULT false,
                is_finalizada BOOLEAN NOT NULL DEFAULT false,
                is_retorno BOOLEAN NOT NULL DEFAULT false,
                is_confirmado BOOLEAN NOT NULL DEFAULT false,
                forma_pagamento VARCHAR(50) NULL,
                is_externa BOOLEAN NOT NULL DEFAULT false,
                referencia_endereco VARCHAR(1000) ,
                valor DECIMAL(10,2) NOT NULL,
                valor_adicional DECIMAL(10,2),
                valor_pago DECIMAL(10,2),
                valor_desconto DECIMAL(10,2),
                data_cadastro TIMESTAMP NOT NULL,
                data_confirmacao TIMESTAMP ,
                data_atendimento TIMESTAMP ,
                data_finalizacao TIMESTAMP ,
                data_cancelamento TIMESTAMP ,
                data_pagamento TIMESTAMP ,
                id_atendente BIGINT NOT NULL,
                id_veterinario BIGINT NOT NULL,
                PRIMARY KEY (id_consulta),
                FOREIGN KEY (id_atendente) REFERENCES funcionario (id_pessoa),
                FOREIGN KEY (id_veterinario) REFERENCES funcionario (id_pessoa),
                FOREIGN KEY (id_prontuario) REFERENCES prontuario (id_prontuario)
);
CREATE SEQUENCE consulta_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE consulta ALTER COLUMN id_consulta SET DEFAULT NEXTVAL('consulta_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS cartao_vacina (
                id_cartao_vacina BIGINT NOT NULL,
                observacao VARCHAR(500),
                dose VARCHAR(25),
                valor DECIMAL(10,2)  NOT NULL,
                data_aplicacao TIMESTAMP,
                data_revacina DATE,
                id_vacina BIGINT NOT NULL,
                id_prontuario BIGINT NOT NULL,
                id_consulta BIGINT NOT NULL,
                situacao  VARCHAR(45) CHECK (situacao in ('APLICADA','PENDENTE','ATRASADA')),
                PRIMARY KEY (id_cartao_vacina),
                FOREIGN KEY (id_vacina) REFERENCES vacina (id_vacina),
                FOREIGN KEY (id_prontuario) REFERENCES prontuario (id_prontuario),
                FOREIGN KEY (id_consulta) REFERENCES consulta (id_consulta)
);
CREATE SEQUENCE cartao_vacina_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE cartao_vacina ALTER COLUMN id_cartao_vacina SET DEFAULT NEXTVAL('cartao_vacina_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS ficha_clinica (
                id_ficha_clinica BIGINT NOT NULL,
                ficha VARCHAR(10000) NOT NULL,
                diagnostico VARCHAR(2000) NOT NULL,
                vermifugacao BOOLEAN DEFAULT FALSE NOT NULL,
                temperatura NUMERIC(10,2) NOT NULL,
                exames_solicitados VARCHAR(2000) NULL,
                tratamento VARCHAR(2000) NULL,
                ativa BOOLEAN NOT NULL,
		data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                data_retorno DATE,
                id_consulta BIGINT NOT NULL,
                id_prontuario BIGINT NOT NULL,
                PRIMARY KEY (id_ficha_clinica),
                FOREIGN KEY (id_consulta) REFERENCES consulta (id_consulta),
                FOREIGN KEY (id_prontuario) REFERENCES prontuario (id_prontuario)
);
CREATE SEQUENCE ficha_clinica_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE ficha_clinica ALTER COLUMN id_ficha_clinica SET DEFAULT NEXTVAL('ficha_clinica_id_seq'::regclass);


CREATE TABLE IF NOT EXISTS exame (
                id_exame BIGINT NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                id_ficha_clinica BIGINT NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                data_exame DATE NOT NULL,
                id_tipo_exame BIGINT NOT NULL,
                PRIMARY KEY (id_exame),
                FOREIGN KEY (id_tipo_exame) REFERENCES tipo_exame (id_tipo_exame)
);
CREATE SEQUENCE exame_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE exame ALTER COLUMN id_exame SET DEFAULT NEXTVAL('exame_id_seq'::regclass);


CREATE TABLE IF NOT EXISTS item_consulta (
                id_item_consulta BIGINT NOT NULL,
                quantidade BIGINT NOT NULL,
                observacao VARCHAR(1000),
                valor_unitario DECIMAL(10,2)  NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                id_exame BIGINT,
                id_produto BIGINT,
                id_medicamento BIGINT,
                id_vacina BIGINT,
                id_ficha_clinica BIGINT NOT NULL,                
                PRIMARY KEY (id_item_consulta),
                FOREIGN KEY (id_exame) REFERENCES exame (id_exame),
                FOREIGN KEY (id_produto) REFERENCES produto (id_produto),
                FOREIGN KEY (id_medicamento) REFERENCES medicamento (id_medicamento),
                FOREIGN KEY (id_ficha_clinica) REFERENCES ficha_clinica (id_ficha_clinica),
                FOREIGN KEY (id_vacina) REFERENCES vacina (id_vacina)
);
CREATE SEQUENCE item_consulta_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE item_consulta ALTER COLUMN id_item_consulta SET DEFAULT NEXTVAL('item_consulta_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS usuario (
                id_usuario BIGINT NOT NULL,
                login VARCHAR(20) NOT NULL,
                senha VARCHAR(500) NOT NULL,
                data_cadastro TIMESTAMP DEFAULT NOW()  NOT NULL,
                data_alteracao TIMESTAMP NOT NULL,
                data_acesso TIMESTAMP,
                ativo BOOLEAN NOT NULL,
                id_funcionario BIGINT NOT NULL,
                PRIMARY KEY (id_usuario),
                FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_pessoa)
);
CREATE SEQUENCE usuario_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE usuario ALTER COLUMN id_usuario SET DEFAULT NEXTVAL('usuario_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS usuario_grupo (                
                id_usuario BIGINT NOT NULL,
                id_grupo BIGINT NOT NULL,
                PRIMARY KEY (id_usuario,id_grupo),
                FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
                FOREIGN KEY (id_grupo) REFERENCES grupo (id_grupo)
) ;


CREATE TABLE IF NOT EXISTS fornecedor (
                id_pessoa BIGINT NOT NULL,
                cpf_cnpj VARCHAR(20) NOT NULL,
                nome_fantasia VARCHAR(250) NOT NULL,
                tipo_negocio VARCHAR(250),
                tipo_fornecedor VARCHAR(250),
                PRIMARY KEY (id_pessoa),
                FOREIGN KEY (id_pessoa) REFERENCES pessoa (id_pessoa)
);
CREATE SEQUENCE fornecedor_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE fornecedor ALTER COLUMN id_pessoa SET DEFAULT NEXTVAL('fornecedor_id_seq'::regclass);

CREATE TABLE IF NOT EXISTS fornecedor_medicamento (                
                id_fornecedor BIGINT NOT NULL,
                id_medicamento BIGINT NOT NULL,
                PRIMARY KEY (id_fornecedor, id_medicamento),
                FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id_pessoa),
                FOREIGN KEY (id_medicamento) REFERENCES medicamento (id_medicamento)
);


CREATE TABLE IF NOT EXISTS fornecedor_produto (               
                id_fornecedor BIGINT NOT NULL,
                id_produto BIGINT NOT NULL,
                PRIMARY KEY (id_fornecedor, id_produto),
                FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id_pessoa),
                FOREIGN KEY (id_produto) REFERENCES produto (id_produto)
);


CREATE TABLE IF NOT EXISTS  cliente (
                id_pessoa BIGINT NOT NULL,
                cpf_cnpj VARCHAR(20),                 
                tipo_cliente VARCHAR(50) NOT NULL,
                is_vip BOOLEAN DEFAULT false NOT NULL,
                PRIMARY KEY (id_pessoa),
                FOREIGN KEY (id_pessoa) REFERENCES pessoa (id_pessoa)
);
CREATE SEQUENCE cliente_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE cliente ALTER COLUMN id_pessoa SET DEFAULT NEXTVAL('cliente_id_seq'::regclass);

CREATE TABLE  IF NOT EXISTS animal (
                id_animal BIGINT NOT NULL,
                nome VARCHAR(500) NOT NULL,
                pelagem VARCHAR(200) NOT NULL,
                sexo VARCHAR(1) NOT NULL,
                peso DECIMAL(10,2) NOT NULL,
                data_nascimento DATE NOT NULL,
                imagem VARCHAR(100) NULL,
                content_type VARCHAR(100) NULL,
                status_animal BOOLEAN NOT NULL,
                id_cliente BIGINT NOT NULL,
                id_raca BIGINT NOT NULL,
                id_cor BIGINT NOT NULL,
                id_prontuario BIGINT NOT NULL,
                PRIMARY KEY (id_animal),
                FOREIGN KEY (id_prontuario) REFERENCES prontuario (id_prontuario),
                FOREIGN KEY (id_cor) REFERENCES cor(id_cor),
                FOREIGN KEY (id_raca) REFERENCES raca(id_raca),
                FOREIGN KEY (id_cliente) REFERENCES cliente (id_pessoa)    
);
CREATE SEQUENCE animal_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE animal ALTER COLUMN id_animal SET DEFAULT NEXTVAL('animal_id_seq'::regclass);
END TRANSACTION;
