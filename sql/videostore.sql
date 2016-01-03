drop database if exists videostoredb;
create database videostoredb;

use videostoredb;

CREATE TABLE usuario (
	id BINARY(16) NOT NULL,
	loginid VARCHAR(16) NOT NULL UNIQUE,
	password VARCHAR(16) NOT NULL,
	email VARCHAR (255) NOT NULL,
	saldo INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE usuario_rol (
	usuarioid BINARY(16) NOT NULL,
	rol ENUM ('registrado', 'admin'),
	PRIMARY KEY (usuarioid, rol),
	FOREIGN KEY (usuarioid) REFERENCES usuario(id) on delete cascade
);

CREATE TABLE auth_tokens (
	usuarioid BINARY(16) NOT NULL,
	token BINARY(16) NOT NULL,
	PRIMARY KEY (token),
	FOREIGN KEY (usuarioid) REFERENCES usuario(id) on delete cascade
);

CREATE TABLE pelicula (
	id BINARY(16) NOT NULL,
	titulo VARCHAR(32) NOT NULL,
	genero VARCHAR(32) NOT NULL,
	ano INTEGER NOT NULL,
	director VARCHAR(32) NOT NULL,
	descripcion VARCHAR(300) NOT NULL,
	votos INTEGER NOT NULL,
	numdescargaspermitidas INTEGER NOT NULL,
	tiempomaximovisualizacion INTEGER NOT NULL,
	precioalquiler INTEGER NOT NULL,
	preciocompra INTEGER NOT NULL,
	fechainclusion TIMESTAMP NOT NULL default current_timestamp,
	PRIMARY KEY (id)
);

CREATE TABLE compradas (
	usuarioid BINARY(16) NOT NULL,
	peliculaid BINARY(16) NOT NULL,
	fechacompra TIMESTAMP NOT NULL,
	numdescargas INTEGER NOT NULL,
	FOREIGN KEY (usuarioid) REFERENCES usuario(id) ON DELETE CASCADE,
	FOREIGN KEY (peliculaid) REFERENCES pelicula(id) ON DELETE CASCADE
);

CREATE TABLE usuarios_alquiler (
	usuarioid BINARY(16) NOT NULL,
	peliculaid BINARY(16) NOT NULL
);

CREATE TABLE alquiladas (
	usuarioid BINARY(16) NOT NULL,
	peliculaid BINARY(16) NOT NULL,
	fechaalquiler TIMESTAMP NOT NULL,
	numvisionadas INTEGER NOT NULL,
	FOREIGN KEY (usuarioid) REFERENCES usuario(id) ON DELETE CASCADE,
	FOREIGN KEY (peliculaid) REFERENCES pelicula(id) ON DELETE CASCADE
);
