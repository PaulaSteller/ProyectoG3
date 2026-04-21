# ProyectoG3
Historia que hicimos 1,2,3,5,6,8,9,10



CREATE DATABASE IF NOT EXISTS proyectog3;
USE proyectog3;


CREATE TABLE usuarios ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(255), 
    correo VARCHAR(255) NOT NULL UNIQUE, 
    password VARCHAR(255) NOT NULL 
);


CREATE TABLE producto ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL, 
    marca VARCHAR(100),
    descripcion VARCHAR(200), 
    precio DECIMAL(10,2), 
    disponible TINYINT(1) DEFAULT 1,
    imagen LONGBLOB 
);


CREATE TABLE servicio ( 
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL, 
    descripcion VARCHAR(200), 
    descripcion_larga VARCHAR(300), 
    precio_base DECIMAL(10,2),
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



INSERT INTO producto (nombre, marca, descripcion, precio, disponible) VALUES 
('Laptop Dell G15', 'Dell', 'Laptop gamer Dell', 850000, 1), 
('ASUS ROG Strix', 'Asus', 'Computadora gamer', 775000, 1), 
('Laptop HP Victus', 'HP', 'Laptop HP alto rendimiento', 750000, 1), 
('Monitor Xiaomi 100HZ', 'Xiaomi', 'Monitor 24 pulgadas', 40900, 1), 
('Mouse Razer Basilisk', 'Razer', 'Mouse gamer', 34000, 1),
('Monitor Asus 165HZ', 'Asus', 'Monitor gamer curvo', 125000, 1),
('Tinta EPSON 673', 'Epson', 'Botella de tinta original', 9500, 1),
('Impresora Epson L4150', 'Epson', 'Sistema de tanque de tinta', 145000, 1);

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
