drop database if exists videostoredb;
create database videostoredb;

SET GLOBAL event_scheduler = ON;

use videostoredb;

CREATE TABLE usuario (
	id BINARY(16) NOT NULL,
	loginid VARCHAR(16) NOT NULL UNIQUE,
	password BINARY(16) NOT NULL,
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
	recursoportada VARCHAR(64) NOT NULL,
	fechainclusion TIMESTAMP NOT NULL default current_timestamp,
	PRIMARY KEY (id)
);

CREATE TABLE compradas (
	usuarioid BINARY(16) NOT NULL,
	peliculaid BINARY(16) NOT NULL,
	fechacompra TIMESTAMP NOT NULL default current_timestamp,
	numdescargas INTEGER NOT NULL,
	FOREIGN KEY (usuarioid) REFERENCES usuario(id) ON DELETE CASCADE,
	FOREIGN KEY (peliculaid) REFERENCES pelicula(id) ON DELETE CASCADE
);

CREATE TABLE votos (
	idpeli BINARY(16) NOT NULL,
	usuarioid BINARY(16) NOT NULL,
	FOREIGN KEY (idpeli) REFERENCES pelicula(id) ON DELETE CASCADE,
	FOREIGN KEY (usuarioid) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE alquiladas (
	usuarioid BINARY(16) NOT NULL,
	peliculaid BINARY(16) NOT NULL,
	fechacompra TIMESTAMP NOT NULL default current_timestamp,
	horasrestantes INTEGER NOT NULL,
	FOREIGN KEY (usuarioid) REFERENCES usuario(id) ON DELETE CASCADE,
	FOREIGN KEY (peliculaid) REFERENCES pelicula(id) ON DELETE CASCADE
);

CREATE TABLE recursos (
	peliculaid BINARY(16) NOT NULL,
	recursopeli VARCHAR(32) NOT NULL,
	FOREIGN KEY (peliculaid) REFERENCES pelicula(id) ON DELETE CASCADE
);



CREATE EVENT e_hora_alquiler ON SCHEDULE EVERY 1 HOUR DO UPDATE videostoredb.alquiladas SET horasrestantes = horasrestantes - 1;
CREATE EVENT e_hora_alquiler_eliminación ON SCHEDULE EVERY 30 MINUTE DO DELETE FROM videostoredb.alquiladas WHERE horasrestantes < 0;
CREATE EVENT e_hora_compras_eliminación ON SCHEDULE EVERY 10 MINUTE DO DELETE FROM videostoredb.compradas WHERE numdescargas < 0;

insert into usuario (id, loginid, password, email, saldo) values (UNHEX(0), 'admin', UNHEX(MD5('videostore')), 'admin@videostore.com', 288);
insert into usuario_rol (usuarioid, rol) values (UNHEX('0'), 'admin');

