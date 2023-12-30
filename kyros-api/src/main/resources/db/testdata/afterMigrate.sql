set foreign_key_checks = 0;

lock tables produto write, cliente write, lancamento write;

delete from produto;
delete from cliente;
delete from lancamento;

set foreign_key_checks = 1;

alter table produto auto_increment = 1;
alter table cliente auto_increment = 1;
alter table lancamento auto_increment = 1;

insert into produto (id, nome, descricao, status, valor_unidade) values (1, 'Cápsulas Nespresso 50 Unidades', 'Os cafés são colhidos por pequenos produtores brasileiros.', 'Ativo', 29.50 );
insert into produto (id, nome, descricao, status, valor_unidade) values (2, 'iPhone 11 Apple 64GB Preto 6,1” 12MP iOS', 'Grave vídeos 4K com o novo sistema de câmera dupla.', 'Ativo', 2699.00); 
insert into produto (id, nome, descricao, status, valor_unidade) values (3, 'Fritadeira Elétrica sem Óleo/Air Fryer Mondial', 'A fritadeira elétrica chega para completar seus momentos na cozinha.', 'Ativo', 309.43);
insert into produto (id, nome, descricao, status, valor_unidade) values (4, 'Pneu Aro 13” 175/70R13 Goodyear 82T Touring', 'Os melhors pneus para seu carro.', 'Ativo', 307.91);
insert into produto (id, nome, descricao, status, valor_unidade) values (5, 'Smart TV 55” UHD 4K LED Samsung 55CU7700', 'Reuna a família e os amigos para assistir algum filme engraçado.', 'Ativo', 2849.65);
insert into produto (id, nome, descricao, status, valor_unidade) values (6, 'Cadeira Gamer Otello Preto e Vermelho', 'Para você que é fanático por games.', 'Ativo', 379.71);
insert into produto (id, nome, descricao, status, valor_unidade) values (7, 'Sanduicheira Amvox AMS 370', 'Deliciosos sanduíches com a Sanduicheira AMS 370 da Amvox.', 'Ativo', 77.89);
insert into produto (id, nome, descricao, status, valor_unidade) values (8, 'Fritadeira Elétrica sem óleo', 'A fritadeira elétrica tem as cores preto e inox.', 'Ativo', 743.07);

insert into cliente (id, nome, data_nascimento, endereco, cpf_cnpj, tipo_pessoa) values (1, 'João da Silva', '2000-10-30','Rua Afonso Pena 2000, Centro - Uberlândia', '95496635900', 'Física');
insert into cliente (id, nome, data_nascimento, endereco, cpf_cnpj, tipo_pessoa) values (2, 'Maria da Silva', '1999-10-21','Rua Afonso Pena 2000, Centro - Uberlândia', '51408596377', 'Física');
insert into cliente (id, nome, data_nascimento, endereco, cpf_cnpj, tipo_pessoa) values (3, 'José Souza', '2002-11-20','Rua Afonso Pena 2000, Centro - Uberlândia', '09033499576', 'Física');
insert into cliente (id, nome, data_nascimento, endereco, cpf_cnpj, tipo_pessoa) values (4, 'Sebastião Martins', '1998-01-31','Rua Afonso Pena 2000, Centro - Uberlândia', '92167733402', 'Física');
insert into cliente (id, nome, data_nascimento, endereco, cpf_cnpj, tipo_pessoa) values (5, 'Manoel Lima', '1980-11-30','Rua Afonso Pena 2000, Centro - Uberlândia', '05134683000193', 'Jurídica');

insert into lancamento (id, cliente_id, produto_id, quantidade_vendida, data_venda,  valor_total_venda) values (1, 1, 6, 1, '2023-12-26', 379.71);
insert into lancamento (id, cliente_id, produto_id, quantidade_vendida, data_venda,  valor_total_venda) values (2, 2, 3, 2, '2023-12-25', 309.43);
insert into lancamento (id, cliente_id, produto_id, quantidade_vendida, data_venda,  valor_total_venda) values (3, 3, 1, 1, '2023-12-24', 65.08);
insert into lancamento (id, cliente_id, produto_id, quantidade_vendida, data_venda,  valor_total_venda) values (4, 4, 4, 1, '2023-12-23', 307.91);
insert into lancamento (id, cliente_id, produto_id, quantidade_vendida, data_venda,  valor_total_venda) values (5, 5, 8, 2, '2023-12-23', 743.07);

unlock tables;