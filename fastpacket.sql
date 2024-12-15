CREATE DATABASE dbfastpacket;
USE dbfastpacket;

-- creacion de usuario desarrollador
CREATE USER 'adminFPp'@localhost IDENTIFIED BY '8h=j?2WCb/4';


GRANT CREATE, ALTER, DROP, INDEX, SELECT, INSERT, UPDATE, DELETE, CREATE TEMPORARY TABLES, EXECUTE 
ON dbfastpacket.* TO 'adminFPp'@'localhost';
FLUSH PRIVILEGES;

-- ver que se hayan dado los permisos correctamente
SHOW GRANTS FOR 'adminFP'@'localhost';

SELECT user, host FROM mysql.user WHERE user = 'adminFP';



CREATE TABLE IF NOT EXISTS cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL,
    apellidoPaterno VARCHAR(20) NOT NULL,
    apellidoMaterno VARCHAR(20) NOT NULL,
    telefono INT,
    correo VARCHAR(50),
    codigoPostal INT NOT NULL,
    calle VARCHAR(30) NOT NULL,
    colonia VARCHAR(30) NOT NULL,
    numeroCasa INT
);

CREATE TABLE IF NOT EXISTS envio (
    numeroDeGuia INT PRIMARY KEY AUTO_INCREMENT,
    costo FLOAT NOT NULL,
    destino VARCHAR(30) NOT NULL,
    ciudad VARCHAR(30) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    calle VARCHAR(30) NOT NULL,
    colonia VARCHAR(30) NOT NULL,
    numero INT,
    codigoPostal INT NOT NULL,
    idCliente INT,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);

CREATE TABLE IF NOT EXISTS rol (
    idRol INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS colaborador (
    idColaborador INT PRIMARY KEY AUTO_INCREMENT,
    correo VARCHAR(50),
    contrasenia VARCHAR(30),
    curp VARCHAR(30),
    nombre VARCHAR(20) NOT NULL,
    apellidoPaterno VARCHAR(20) NOT NULL,
    apellidoMaterno VARCHAR(20) NOT NULL,
    noPersonal INT,
    idRol INT NOT NULL,
    FOREIGN KEY (idRol) REFERENCES rol(idRol)
);

CREATE TABLE IF NOT EXISTS tipoUnidad (
    idTipoUnidad INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS unidad (
    idUnidad INT PRIMARY KEY AUTO_INCREMENT,
    vin INT,
    nii VARCHAR(30),
    modelo VARCHAR(30),
    marca VARCHAR(30),
    motivo VARCHAR(60),
    idTipoUnidad INT NOT NULL,
    FOREIGN KEY (idTipoUnidad) REFERENCES tipoUnidad(idTipoUnidad)
);

CREATE TABLE IF NOT EXISTS paquete (
    idPaquete INT PRIMARY KEY AUTO_INCREMENT,
    peso FLOAT,
    dimensiones FLOAT,
    descripcion VARCHAR(60),
    numeroDeGuia INT,
    idUnidad INT,
    FOREIGN KEY (numeroDeGuia) REFERENCES envio(numeroDeGuia),
    FOREIGN KEY (idUnidad) REFERENCES unidad(idUnidad)
);


-- insercion de registros
-- tabla cliente
INSERT INTO cliente (nombre, apellidoPaterno, apellidoMaterno, telefono, correo, codigoPostal, calle, colonia, numeroCasa) 
VALUES
('Juan', 'Pérez', 'Gómez', 095554, 'juanperez@paqueteria.com', 64000, 'Calle Falsa', 'Colonia Centro', 101),
('Ana', 'López', 'Martínez', 987654, 'analopez@paqueteria.com', 64010, 'Av. Reforma', 'Colonia Norte', 202),
('Carlos', 'García', 'Rodríguez', 987654, 'carlosgarcia@paqueteria.com', 64020, 'Calle 10', 'Colonia Sur', 303),
('Laura', 'Hernández', 'Sánchez', 124669, 'laurahernandez@paqueteria.com', 64030, 'Calle Luna', 'Colonia Oeste', 404),
('Luis', 'Gómez', 'Ruiz', 764321, 'luisgomez@paqueteria.com', 64040, 'Av. Hidalgo', 'Colonia Este', 505),
('Marta', 'Ramírez', 'Díaz', 953115, 'martaramirez@paqueteria.com', 64050, 'Calle Sol', 'Colonia Norte', 606);

--  tabla envio
INSERT INTO envio (costo, destino, ciudad, estado, calle, colonia, numero, codigoPostal, idCliente) 
VALUES
(150.75, 'México', 'Ciudad de México', 'CDMX', 'Calle Falsa', 'Colonia Centro', 101, 64000, 1),
(200.50, 'Guadalajara', 'Jalisco', 'Jalisco', 'Av. Reforma', 'Colonia Norte', 202, 64010, 2),
(120.30, 'Monterrey', 'Nuevo León', 'Nuevo León', 'Calle 10', 'Colonia Sur', 303, 64020, 3),
(175.60, 'Puebla', 'Puebla', 'Puebla', 'Calle Luna', 'Colonia Oeste', 404, 64030, 4),
(180.00, 'Cancún', 'Quintana Roo', 'Quintana Roo', 'Av. Hidalgo', 'Colonia Este', 505, 64040, 5),
(250.80, 'Tijuana', 'Baja California', 'Baja California', 'Calle Sol', 'Colonia Norte', 606, 64050, 6);

-- tabla rol
INSERT INTO rol (tipo) 
VALUES
('Administrador'),
('Ejecutivo de tienda'),
('Conductor');

-- tabla colaborador
INSERT INTO colaborador (correo, contrasenia, curp, nombre, apellidoPaterno, apellidoMaterno, noPersonal, idRol) 
VALUES
('juanperez@paqueteria.com', 'r1934', 'PEJ123456HDFRRL01', 'Juan', 'Pérez', 'Gómez', 1001, 1),
('analopez@paqueteria.com', 'r1235', 'LOA987654MDFRRL02', 'Ana', 'López', 'Martínez', 1002, 2),
('carlosgarcia@paqueteria.com', 'c1230', 'GAR13579HDFRRL03', 'Carlos', 'García', 'Rodríguez', 1003, 3),
-- nuevos valores
('laurahernandez@paqueteria.com', 'r1203', 'HER24680MDFRRL04', 'Laura', 'Hernández', 'Sánchez', 1004, 2),
('luisgomez@paqueteria.com', 'r1236', 'GOM112233HDFRRL05', 'Luis', 'Gómez', 'Ruiz', 1005, 3),
('martaramirez@paqueteria.com', 'c1247', 'RAM112233MDFRRL06', 'Marta', 'Ramírez', 'Díaz', 1006, 2),
('edgar@paqueteria.com', 'e1289', 'EDJ927982MDFRRL06', 'Edgar', 'Juarez', 'Cadena', 1007, 3);

--  tabla tipoUnidad
INSERT INTO tipoUnidad (tipo) 
VALUES
('Gasolina'),
('Disel'),
('Electrica'),
('Hibrida');

--  tabla unidad
INSERT INTO unidad (vin, nii, modelo, marca, motivo, idTipoUnidad) 
VALUES
(235789, 'VIN12345', 'Furgoneta 2024', 'Ford', 'Transporte mercancía pesada', 1),
(646701, 'VIN23456', 'Furgoneta 2023', 'Toyota', 'Transporte personal y paquetes pequeños', 2),
(346012, 'VIN34567', 'Motocicleta 2022', 'Honda', 'Entrega urgente de paquetes pequeños', 3),
(456733, 'VIN45678', 'Furgoneta 2023', 'Mercedes-Benz', 'Transporte de carga pesada', 4);


-- tabla paquete
INSERT INTO paquete (peso, dimensiones, descripcion, numeroDeGuia, idUnidad) 
VALUES
(10.5, 2.3, 'Paquete pequeño', 1, 1),
(15.0, 3.0, 'Paquete mediano', 2, 2),
(5.2, 1.8, 'Paquete urgente', 3, 3),
(25.8, 4.5, 'Paquete grande', 4, 4),
(3.7, 2.1, 'Paquete frágil', 5, 1),
(12.4, 3.5, 'Paquete voluminoso', 6, 3);