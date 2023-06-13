
Create database Mercado;
use Mercado;

Create table vendas(
	idVendas bigint auto_increment primary key,
	qntidade int,
	total numeric(10,2),
	dtVenda Date,
	hVenda Time
);

Create table produto(
	codProduto bigint primary key,
	nome varchar(30),
	descricao varchar(80),
	precoUnit numeric(10,2),
	qntProd int,
	idVend bigint,
	foreign key (idVend) references vendas (idVendas)
);