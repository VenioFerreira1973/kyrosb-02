create table produto(
	id bigint not null auto_increment,
	nome varchar(60) not null,
	descricao varchar(200) not null,
	status varchar(8) not null,
	valor_unidade decimal(10,2) not null,
	primary key(id)
) engine=InnoDB default charset=utf8;


create table cliente (
	id bigint not null auto_increment, 
	nome varchar(80) not null,
	data_nascimento datetime not null, 
	endereco varchar(200) not null,
	cpf_cnpj varchar(15) not null,
	tipo_pessoa varchar(10) not null, 
	primary key(id)
) engine=InnoDB default charset=utf8;

create table lancamento(
	id bigint not null auto_increment,
	cliente_id bigint not null,
	produto_id bigint not null,
	quantidade_vendida smallint(6) not null,
	data_venda datetime not null,
	valor_total_venda decimal(10,2) not null,
	primary key(id),
	unique key uk_lancamento_cliente_produto (id, produto_id, cliente_id),
	constraint fk_lancamento_cliente foreign key (cliente_id) references cliente (id),
	constraint fk_lancamento_produto foreign key (produto_id) references produto (id)
	
) engine=InnoDB default charset=utf8;


