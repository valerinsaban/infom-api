-- Catalogos

CREATE TABLE [dbo].[departamentos] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255)
);

CREATE TABLE [dbo].[municipios] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255),
  [id_departamento] int,
  CONSTRAINT [fk_departamentos_municipios] FOREIGN KEY ([id_departamento]) REFERENCES [dbo].[departamentos] ([id])
);

CREATE TABLE [dbo].[garantias] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255)
);

CREATE TABLE [dbo].[profesiones] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255)
);

CREATE TABLE [dbo].[regiones] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255)
);

CREATE TABLE [dbo].[tipos_prestamos] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255)
);

CREATE TABLE [dbo].[regionales] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255),
  [direccion] varchar(255),
  [telefono] varchar(255),
  [correo] varchar(255),
  [encargado] varchar(255)
);

CREATE TABLE [dbo].[puestos] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255)
);

CREATE TABLE [dbo].[bancos] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255),
  [siglas] varchar(255)
);

CREATE TABLE [dbo].[estados_civiles] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
  [nombre] varchar(255)
);

-- Seguridad

CREATE TABLE [dbo].[menus] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [nombre] varchar(255),
  [url] varchar(255),
  [icono] varchar(255),
  [orden] int
);

CREATE TABLE [dbo].[submenus] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [nombre] varchar(255),
  [url] varchar(255),
  [orden] int,
  [id_menu] int,
  CONSTRAINT [fk_submenus_menus] FOREIGN KEY ([id_menu]) REFERENCES [dbo].[menus] ([id])
);

CREATE TABLE [dbo].[roles] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [nombre] varchar(255),
  [color] varchar(255)
);

CREATE TABLE [dbo].[usuarios] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [nombre] varchar(255),
  [apellido] varchar(255),
  [dpi] varchar(255),
  [usuario] varchar(255),
  [clave] varchar(255),
  [id_regional] int,
  [id_rol] int,
  CONSTRAINT [fk_usuarios_regionales] FOREIGN KEY ([id_regional]) REFERENCES [dbo].[regionales] ([id]),
  CONSTRAINT [fk_usuarios_roles] FOREIGN KEY ([id_rol]) REFERENCES [dbo].[roles] ([id])
);

CREATE TABLE [dbo].[usuarios_roles] (
  [id_usuario] int NOT NULL,
  [id_rol] int NOT NULL,
);

CREATE TABLE [dbo].[permisos] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [accion] varchar(255),
  [id_rol] int,
  [id_menu] int,
  [id_submenu] int,
  CONSTRAINT [fk_permisos_roles] FOREIGN KEY ([id_rol]) REFERENCES [dbo].[roles] ([id]),
  CONSTRAINT [fk_permisos_menus] FOREIGN KEY ([id_menu]) REFERENCES [dbo].[menus] ([id]),
  CONSTRAINT [fk_permisos_submenus] FOREIGN KEY ([id_submenu]) REFERENCES [dbo].[submenus] ([id])
);

-- Generales

CREATE TABLE [dbo].[municipalidades] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [direccion] varchar(255),
  [id_departamento] int,
  [id_municipio] int,
  CONSTRAINT [fk_municipalidades_departamentos] FOREIGN KEY ([id_departamento]) REFERENCES [dbo].[departamentos] ([id]),
  CONSTRAINT [fk_municipalidades_municipios] FOREIGN KEY ([id_municipio]) REFERENCES [dbo].[municipios] ([id])
);

CREATE TABLE [dbo].[aportes] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [anio] int,
  [mes] int,
  [monto] varchar(255),
  [id_municipalidad] int,
  [id_garantia] int,
  CONSTRAINT [fk_aportes_municipalidades] FOREIGN KEY ([id_municipalidad]) REFERENCES [dbo].[municipalidades] ([id]),
  CONSTRAINT [fk_aportes_garantias] FOREIGN KEY ([id_garantia]) REFERENCES [dbo].[garantias] ([id])
);

CREATE TABLE [dbo].[funcionarios] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [nombre] varchar(255),
  [apellido] varchar(255),
  [fecha_nacimiento] date,
  [dpi] varchar(255),
  [carnet] varchar(255),
  [fecha_carnet] date,
  [acta_toma_posecion] varchar(255),
  [fecha_acta_toma_posecion] date,
  [estado] varchar(255),
  [imagen_carnet] varchar(max),
  [imagen_acta_toma_posecion] varchar(max),
  [imagen_fotografia] varchar(max),
  [imagen_firma] varchar(max),
  [id_municipalidad] int,
  [id_puesto] int,
  [id_profesion] int,
  [id_estado_civil] int,
  CONSTRAINT [fk_funcionarios_municipalidades] FOREIGN KEY ([id_municipalidad]) REFERENCES [dbo].[municipalidades] ([id]),
  CONSTRAINT [fk_funcionarios_puestos] FOREIGN KEY ([id_puesto]) REFERENCES [dbo].[puestos] ([id]),
  CONSTRAINT [fk_funcionarios_profesiones] FOREIGN KEY ([id_profesion]) REFERENCES [dbo].[profesiones] ([id]),
  CONSTRAINT [fk_funcionarios_estados_civiles] FOREIGN KEY ([id_estado_civil]) REFERENCES [dbo].[estados_civiles] ([id])
);

CREATE TABLE [dbo].[prestamos] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [no_dictamen] varchar(255),
  [no_pagare] varchar(255),
  [fecha] date,
  [fecha_vencimiento] date,
  [monto] varchar(255),
  [plazo_meses] int,
  [fecha_acta] date,
  [deposito_intereses] varchar(255),
  [intereses] varchar(255),
  [intereses_fecha_fin] date,
  [tiempo_gracia] int,
  [destino_prestamo] varchar(255),
  [cobro_intereses] bit,
  [acta] varchar(255),
  [punto] varchar(255),
  [fecha_memorial] date,
  [autorizacion] varchar(255),
  [certficacion] varchar(255),
  [oficioaj] date,
  [oficioaj2] varchar(255),
  [estado] varchar(255),
  [id_tipo_prestamo] int,
  [id_municipalidad] int,
  [id_funcionario] int,
  [id_regional] int,
  [id_usuario] int,
  CONSTRAINT [fk_prestamos_tipos_prestamos] FOREIGN KEY ([id_tipo_prestamo]) REFERENCES [dbo].[tipos_prestamos] ([id]),
  CONSTRAINT [fk_prestamos_municipalidades] FOREIGN KEY ([id_municipalidad]) REFERENCES [dbo].[municipalidades] ([id]),
  CONSTRAINT [fk_prestamos_funcionarios] FOREIGN KEY ([id_funcionario]) REFERENCES [dbo].[funcionarios] ([id]),
  CONSTRAINT [fk_prestamos_regionales] FOREIGN KEY ([id_regional]) REFERENCES [dbo].[regionales] ([id]),
  CONSTRAINT [fk_prestamos_usuarios] FOREIGN KEY ([id_usuario]) REFERENCES [dbo].[usuarios] ([id])
);

CREATE TABLE [dbo].[amortizaciones] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [fecha_inicio] date,
  [fecha_fin] date,
  [dias] int,
  [capital] varchar(255),
  [intereses] varchar(255),
  [iva_intereses] varchar(255),
  [cuota] varchar(255),
  [saldo] varchar(255),
  [id_prestamo] int,
  CONSTRAINT [fk_amortizaciones_prestamos] FOREIGN KEY ([id_prestamo]) REFERENCES [dbo].[prestamos] ([id])
);

-- Data

INSERT INTO [departamentos] ([codigo], [nombre]) VALUES 
('01', 'GUATEMALA'),
('02', 'EL PROGRESO'),
('03', 'SACATEPÉQUEZ'),
('04', 'CHIMALTENANGO'),
('05', 'ESCUINTLA'),
('06', 'SANTA ROSA'),
('07', 'SOLOLA'),
('08', 'TOTONICAPAN'),
('09', 'QUETZALTENANGO'),
('10', 'SUCHITEPEQUEZ'),
('11', 'RETALHULEU'),
('12', 'SAN MARCOS'),
('13', 'HUEHUETENANGO'),
('14', 'EL QUICHE'),
('15', 'BAJA VERAPA'),
('16', 'ALTA VERAPAZ'),
('17', 'EL PETEN'),
('18', 'IZABAL'),
('19', 'ZACAPA'),
('20', 'CHIQUIMULA'),
('21', 'JALAPA'),
('22', 'JUTIAPA');

INSERT INTO [municipios] ([codigo], [nombre], [id_departamento]) VALUES 
('01', 'GUATEMALA', 1);

INSERT INTO [estados_civiles] ([codigo], [nombre]) VALUES 
('01', 'Soltero'),
('02', 'Casado');

INSERT INTO [garantias] ([codigo], [nombre]) VALUES 
('01', 'Aporte IVA PAZ'),
('02', 'Aporte Constitucional');

INSERT INTO [profesiones] ([codigo], [nombre]) VALUES 
('01', 'Profesion 1'),
('02', 'Profesion 2');

INSERT INTO [regiones] ([codigo], [nombre]) VALUES 
('01', 'Occidental'),

INSERT INTO [tipos_prestamos] ([codigo], [nombre]) VALUES 
('01', 'Tipo Prestamo 1'),

INSERT INTO [puestos] ([codigo], [nombre]) VALUES 
('01', 'Alcalde'),

INSERT INTO [bancos] ([codigo], [nombre], [siglas]) VALUES 
('01', 'Banco Banrural', 'BRN'),

INSERT INTO [regionales] ([codigo], [nombre], [direccion], [telefono], [correo], [encargado]) VALUES 
('01', 'INFOM CENTRAL', NULL, NULL, NULL, NULL);

INSERT INTO [roles] ([nombre], [color]) VALUES 
('ADMIN', '#008fc7'),
('RECEPCION', '#0ebe4c'),
('OPERATIVO', '#36ab97');

INSERT INTO [usuarios] ([nombre], [apellido], [dpi], [usuario], [clave], [id_regional], [id_rol]) VALUES 
('Administrador', 'Central', '000000000000', 'admin', '$2a$10$Mhw/7yJnqEcSk5gPk77ro.72NMOmlcgkJnnd5q9uyX/MYGRR5hl96', 1, 1);

INSERT INTO [menus] ([nombre], [url], [icono], [orden]) VALUES 
('Municipalidades', 'municipalidades', 'fad fa-house-building', 1),
('Funcionarios', 'funcionarios', 'fad fa-user-group-crown', 2),
('Prestamos', 'prestamos', 'fad fa-money-check-dollar-pen', 3),
('Catalogos', NULL, 'fad fa-house-building', 9),
('Seguridad', NULL, 'fad fa-shield-check', 10);

INSERT INTO [submenus] ([nombre], [url], [orden], [id_menu]) VALUES 
('Departamentos', 'departamentos', 1, 4),
('Municipios', 'municipios', 2, 4),
('Estados Civiles', 'estados-civiles', 3, 4),
('Puestos', 'puestos', 4, 4),
('Regiones', 'regiones', 5, 4),
('Garantias', 'garantias', 6, 4),
('Regionales', 'regionales', 7, 4),
('Profesiones', 'profesiones', 8, 4),
('Bancos', 'bancos', 9, 4),
('Tipos Prestamos', 'tipos-prestamos', 10, 4),
('Usuarios', 'usuarios', 1, 5),
('Roles', 'roles', 2, 5),
('Permisos', 'permisos', 3, 5);