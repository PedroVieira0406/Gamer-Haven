-- Empresas
INSERT INTO empresa (nome, cnpj, email) 
VALUES 
    ('Nintendo', '12345678901211', 'nintendo@gmail.com'),
    ('Riot', '12398765401211', 'riot@gmail.com');

-- Temas
INSERT INTO tema (nome) 
VALUES 
    ('Terror'),
    ('Tiro'),
    ('Cooperativo'),
    ('2d');

-- Plataformas
INSERT INTO plataforma (nome) 
VALUES 
    ('Playstation'),
    ('Xbox'),
    ('Console'),
    ('Android');

-- Jogos
INSERT INTO jogo (nome, preco, desconto, faixaEtaria, empresa_id) 
VALUES
    ('Mario', 200.00, 0.01, 'LIVRE', 1),
    ('Valorant', 0.01, 0.01, 'DOZE_ANOS', 2);

-- Relação entre Jogos e Plataformas
INSERT INTO jogo_plataforma (idjogo, idplataforma) 
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4);

-- Relação entre Jogos e Temas
INSERT INTO jogo_tema (idjogo, idtema) 
VALUES
    (1, 1),
    (1, 4),
    (2, 2),
    (2, 3);

-- Logins
INSERT INTO login (name, senha) 
VALUES 
    ('Cria01', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA=='),
    ('roBerto02', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA=='),
    ('Florzinha', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA=='),
    ('JesscaAra', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA=='),
    ('Deus-Sama', 'AblfspGdnCpnKpc5+AZ5EIfxn4QitFScugvcCx0ZKpl1BACS9gauXq8GpH3uIl7X0n8jkLXlvKUaXKeEmDm3NQ==');

-- Clientes
INSERT INTO cliente (nome, email, cpf, login_id, idade) 
VALUES 
    ('João Silva', 'joao@gmail.com', '12345678910', 1, 32),
    ('Roberto Pereira', 'robertin@gmail.com', '43215678910', 2, 32);

-- Funcionários
INSERT INTO funcionario (nome, email, cpf, login_id, cargo)
VALUES 
    ('Ana Santos', 'ana@gmail.com', '01987654321', 3, 'Auxiliar'),
    ('Jessica Aragão', 'jessica@gmail.com', '01984567321', 4, 'Administrador'),
    ('Deus Sama', 'God@gmail.com', '0000000001', 5, 'DEUS');

-- Biblioteca
INSERT INTO biblioteca (idCliente, idJogo)
VALUES
    (1, 1),
    (2, 2);

-- Cartao
INSERT INTO cartao (cartaoMarca, nCartao, mesValid, anoValid, numSeguranca, nome, sobrenome, cep, cidade, pais, idCLiente)
VALUES
    ('VISA', '1234567890123456', 12, 2024, 'j+Z+xmgZtP05avyaIysXHHnNH/N4LFtr6WCgYGgpMciYGzWUtilGg1bl7LQ2n4sadR/BQJEXjMrk1XRFg2jMSg==', 'roberto', 'Silva', 'São Paulo', '12345-678', 'Brasil', 1),
    ('JCB', '1231544820132932', 9, 2025, 'j+Z+xmgZtP05avyaIysXHHnNH/N4LFtr6WCgYGgpMciYGzWUtilGg1bl7LQ2n4sadR/BQJEXjMrk1XRFg2jMSg==', 'Carla', 'Andrade', 'Rio de Janeiro', '54321-678', 'Brasil', 2);

-- Pagamento
INSERT INTO pagamento( cliente_id, status,valorcompra, valorPago, modoPagamento, cartao_id)
VALUES
(1, TRUE,000.0, 000.0, 'PIX', null),
(1, TRUE,200.0, 200.0, 'PIX', null),
(1, TRUE,200.0, 200.0, 'CREDITO', 1);

-- Compras
INSERT INTO compra (preco, idPagante, idDono, idJogo,idPagamento)
VALUES
    (0.0, 1, 2, 2,1),
    (199.9, 1, 1, 1,2),
    (199.9, 1, 2, 1,3);
