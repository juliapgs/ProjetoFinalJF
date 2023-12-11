-- Dados do banco de dados --

-- Usuários para login
insert into usuario (nome, login, senha, perfil) values('Elon Musk', 'musk', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into usuario (nome, login, senha, perfil) values('Bill Gates', 'gates', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);

-- Numeros de telefone
insert into telefone (codigoArea, numero) values('63', '9999-9999');
insert into telefone (codigoArea, numero) values('62', '8888-8888');
insert into telefone (codigoArea, numero) values('61', '7777-7777');
insert into telefone (codigoArea, numero) values('55', '6666-6666');

-- Enderecos
insert into endereco (cep, complemento) values('77023', 'Quadra 904 sul, lote 11');
insert into endereco (cep, complemento) values('66600', 'Quadra 804 sul, lote 37');
insert into endereco (cep, complemento) values('77700', 'Quadra 1206 sul, lote 71');

-- Relação UsuarioTelefone
insert into usuario_telefone (id_usuario, id_telefone) values(1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values(1, 2);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 3);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 4);

-- Relação pedidoEndereco
-- insert into pedido_endereco (id_pedido, id_endereco) values(1,1);
-- insert into pedido_endereco (id_pedido, id_endereco) values(1,2);
-- insert into pedido_endereco (id_pedido, id_endereco) values(2,3);

-- Inserindo Tenis
insert into tenis (marca, modelo, categoria, cor, tamanho, valor) values('Nike', 'Air Force 1', 'CASUAL', 'Branco', 41, 799.99);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor) values('Nike', 'Dunk Low SB', 'SKATE', 'Preto', 40, 899.99);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor) values('Nike', 'Jordan 1', 'BASQUETE', 'Vermelho', 44, 1299.99);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor) values('Adidas', 'Bad Bunny', 'CASUAL', 'Azul', 41, 799.99);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor) values('Adidas', 'Ultraboost', 'CORRIDA', 'Branco', 41, 999.99);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor) values('Vans', 'Old Skool', 'SKATE', 'Preto', 41, 399.99);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor) values('Nike', 'Zoom Fly', 'CORRIDA', 'Rosa', 37, 499.99);

-- Inserindo Pedidos
INSERT INTO pedido (id_usuario, id_tenis, dataCompra, formaPagamento, statusPedido) VALUES (1, 2, '2023-02-22', 'PIX', 'AGUARDANDOPAGAMENTO');
INSERT INTO pedido (id_usuario, id_tenis, dataCompra, formaPagamento, statusPedido) VALUES (1, 2, '2023-04-03', 'BOLETO', 'ENTREGUE');
INSERT INTO pedido (id_usuario, id_tenis, dataCompra, formaPagamento, statusPedido) VALUES (2, 3, '2023-10-06', 'PIX', 'PAGO');