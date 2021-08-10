insert into grupo values (1,"ADMINISTRADOR","ADMINISTRADOR"),
(2,"VETERINARIO","VETERINARIO"),
(3,"ATENDENTE","ATENDENTE");

insert into permissao values
(1, 'ROLE_CADASTRAR_FUNCIONARIO', 'CADASTRAR_FUNCIONARIO'), 
(2, 'ROLE_CADASTRAR_CLIENTE', 'CADASTRAR_CLIENTE'),
(3, 'ROLE_CADASTRAR_CONSULTA','CADASTRAR_CONSULTA');

INSERT INTO grupo_permissao VALUES
(1,1),
(1,2),
(1,3);


INSERT INTO usuario_grupo values
((select id_usuario from usuario where login = 'admin'),1);