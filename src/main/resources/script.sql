create database vertebrados;

use  vertebrados;
create database vertebrados;

use  vertebrados;

CREATE TABLE IF NOT EXISTS Animales (
    id_animal INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cientifico VARCHAR(255) NOT NULL,
    nombre_comun VARCHAR(255),
    clasificacion  VARCHAR(255),
    usuario  VARCHAR(255)
);