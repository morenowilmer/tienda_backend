--CREATE SCHEMA tienda;

CREATE TABLE tipo_documento (
	id INT PRIMARY KEY,
    nombre varchar(100),
    valor varchar(6),
    activo varchar(1)
);

CREATE TABLE persona (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(30) NOT NULL,
	apellido VARCHAR(30) NOT NULL,
	tipo_doc int,
	identificacion varchar(30),
    correo varchar(60),
	celular varchar(20),
	fecha_nacimiento date,
	departamento varchar(100),
	ciudad varchar(100),
    direccion varchar(250),
	foreign key (tipo_doc) references tipo_documento(id)
);

create table usuario (
	usuario varchar(20) primary key not null,
    contrasena varchar(120) not null,
    per_id int not null,
    activo varchar(2),
    foreign key (per_id) references persona(id)
);

create table categortia_producto (
	id int auto_increment primary key,
    nombre varchar(70) not null,
    descripcion varchar(250) null
);


create table producto (
	id int auto_increment primary key,
    nombre varchar(70) not null,
    descripcion varchar(250) null,
    cantidad int not null,
    codigo_barras varchar(50) null,
    precio_compra int not null,
    precio_venta int not null,
    id_categoria int not null,
    foreign key (id_categoria) references categortia_producto(id)
);

create table factura (
	id char(36) primary key not null,
    total_articulos int not null,
    total_venta int not null,
    total_impuesto int not null,
    total_factura int not null,
    fecha_compra datetime not null
);

create table detalle_factura (
	id int auto_increment primary key,
    id_factura char(36) not null,
    id_producto int not null,
    total_items int not null,
    precio_producto int not null,
    valor_total int not null,
    foreign key (id_factura) references factura(id)
);