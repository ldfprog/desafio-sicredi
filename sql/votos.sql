create database votos;

use votos;

create table Pauta (
	id int AUTO_INCREMENT,
	descricao varchar(255) NOT NULL UNIQUE,
	primary key (id)
);

create table Associado (
	id int AUTO_INCREMENT,
	cpf varchar(11) NOT NULL UNIQUE,
	primary key (id)
);

create table Sessao (
	id int AUTO_INCREMENT,
	pauta_id int NOT NULL,
	duracao int not null,
	data_criacao datetime NOT NULL,
	primary key (id),
    foreign key (pauta_id) references Pauta(id)
);

create table Voto (
	id int AUTO_INCREMENT,
	sessao_id int NOT NULL,
	associado_id int NOT NULL,
	voto bit NOT NULL,
	data_voto datetime NOT null,
	primary key (id),
    foreign key (sessao_id) references Sessao(id),
    foreign key (associado_id) references Associado(id)
);