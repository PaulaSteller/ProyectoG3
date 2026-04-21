# ProyectoG3
Historia que hicimos 1,2,3,5,6,8,9,10

CREATE DATABASE proyectog3;

USE proyectog3;

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    precio DECIMAL(10,2),
    imagen LONGBLOB
);

CREATE TABLE servicio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    descripcion_larga VARCHAR(300),
    imagen LONGBLOB
);

CREATE TABLE orden_servicio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_orden VARCHAR(50) NOT NULL UNIQUE,
    cliente VARCHAR(100) NOT NULL,
    equipo VARCHAR(150) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha_estimada DATE
);


INSERT INTO producto (nombre, descripcion, precio) VALUES
('Laptop Dell G15','Laptop gamer Dell',850000),
('ASUS ROG Strix','Computadora gamer',775000),
('Laptop HP Victus','Laptop HP alto rendimiento',750000),
('Monitor Xiaomi A24i','Monitor 24 pulgadas',40900),
('Mouse Razer Basilisk','Mouse gamer',34000);


INSERT INTO servicio (nombre, descripcion, descripcion_larga) VALUES
('Reparación','Reparación de computadoras','Diagnóstico completo de hardware'),
('Tintas','Venta de tintas','Recarga y venta de cartuchos'),
('Láser','Impresoras láser','Mantenimiento de impresoras láser'),
('Mantenimiento','Mantenimiento preventivo','Limpieza interna de equipos');

INSERT INTO orden_servicio (numero_orden, cliente, equipo, estado, fecha_estimada) VALUES
('ORD-1001', 'Juan Perez', 'Laptop Dell G15 - Cambio de pasta termica', 'Recibido', '2026-04-25'),
('ORD-1002', 'Maria Gonzalez', 'Impresora Epson L4150 - Limpieza de cabezales', 'En revision', '2026-04-22'),
('ORD-1003', 'Carlos Rojas', 'PC Escritorio - Instalacion Dual Channel', 'Reparado', '2026-04-20'),
('ORD-1004', 'Ana Valverde', 'Laptop HP Victus - Cambio de pantalla', 'Entregado', '2026-04-18');


UPDATE producto 
SET imagen = FROM_BASE64(
'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=')
WHERE id = 1;

UPDATE producto 
SET imagen = FROM_BASE64(
'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=')
WHERE id = 2;

UPDATE producto 
SET imagen = FROM_BASE64(
'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=')
WHERE id = 3;

UPDATE producto 
SET imagen = FROM_BASE64(
'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=')
WHERE id = 4;

UPDATE producto 
SET imagen = FROM_BASE64(
'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR4nGNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=')
WHERE id = 5;
