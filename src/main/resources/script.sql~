create database vertebrados;

use  vertebrados;

CREATE TABLE IF NOT EXISTS Clasificacion (
     id_clasificacion INT AUTO_INCREMENT PRIMARY KEY,
     nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Perfiles (
    id_perfil INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    permisos VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    id_perfil INT,
    FOREIGN KEY (id_perfil) REFERENCES Perfiles(id_perfil)
);

CREATE TABLE IF NOT EXISTS Animales (
    id_animal INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cientifico VARCHAR(255) NOT NULL,
    nombre_comun VARCHAR(255),
    id_clasificacion INT,
    id_usuario INT,
    FOREIGN KEY (id_clasificacion) REFERENCES Clasificacion(id_clasificacion),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS Caracteristicas (
   id_caracteristica INT AUTO_INCREMENT PRIMARY KEY,
   id_animal INT,
   dieta VARCHAR(255),
    habitat VARCHAR(255),
    esperanza_vida INT,
    FOREIGN KEY (id_animal) REFERENCES Animales(id_animal)
);



