insert into empresa (nome, cnpj, email, senha) 
values('Nintendo', '12345678901211', 'nintendo@gmail.com', 'asdasd');
insert into empresa (nome, cnpj, email, senha) 
values ('Riot', '12398765401211', 'riot@gmail.com', 'asdasddasd');

insert into tema (nome) values ('Terror');
insert into tema (nome) values ('Romance');
insert into tema (nome) values ('Suspense');

insert into plataforma (nome) values ('Playstation');
insert into plataforma (nome) values ('Xbox');
insert into plataforma (nome) values ('Console');
insert into plataforma (nome) values ('Android');

INSERT INTO jogo (nome, preco, faixaEtaria, empresa_id) VALUES
('Mario', 199.99, 'LIVRE', 1);

INSERT INTO jogo_plataforma (idjogo, idplataforma) VALUES
(1, 1),
(1, 2),
(1, 3);  

INSERT INTO jogo_tema (idjogo, idtema) VALUES
(1, 1),
(1, 2);

INSERT INTO cliente (nome, email, cpf, senha, idade)
VALUES ('João Silva', 'joao@gmail.com', '12345678910', 'senha123', 32);

INSERT INTO funcionario (nome, email, cpf, senha, cargo)
VALUES ('Ana Santos', 'ana@gmail.com', '01987654321', 'asdaasd', 'Gerente');