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
  [correo] varchar(255),
  [telefono] varchar(255),
  [no_cuenta] varchar(255),
  [id_departamento] int,
  [id_municipio] int,
  [id_banco] int,
  CONSTRAINT [fk_municipalidades_departamentos] FOREIGN KEY ([id_departamento]) REFERENCES [dbo].[departamentos] ([id]),
  CONSTRAINT [fk_municipalidades_municipios] FOREIGN KEY ([id_municipio]) REFERENCES [dbo].[municipios] ([id]),
  CONSTRAINT [fk_municipalidades_bancos] FOREIGN KEY ([id_banco]) REFERENCES [dbo].[bancos] ([id])
);

CREATE TABLE [dbo].[importes] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [mes] varchar(255),
  [fecha] date,
  [observaciones] varchar(255),
  [constitucional] varchar(255),
  [iva_paz] varchar(255),
  [vehiculos] varchar(255),
  [petroleo] varchar(255),
  [total] varchar(255)
);

CREATE TABLE [dbo].[aportes] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [mes] varchar(255),
  [constitucional] varchar(255),
  [iva_paz] varchar(255),
  [vehiculos] varchar(255),
  [petroleo] varchar(255),
  [total] varchar(255),
  [codigo_departamento] varchar(255),
  [codigo_municipio] varchar(255),
  [id_importe] int,
  CONSTRAINT [fk_aportes_importes] FOREIGN KEY ([id_importe]) REFERENCES [dbo].[importes] ([id]) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE [dbo].[funcionarios] (
  [id] int IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [codigo] varchar(255),
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
('0101', 'Municipio de Guatemala', 1),
('0102', 'Santa Catarina Pinula', 1),
('0103', 'San José Pinula', 1),
('0104', 'San José del Golfo', 1),
('0105', 'Palencia', 1),
('0106', 'Chinautla', 1),
('0107', 'San Pedro Ayampuc', 1),
('0108', 'Mixco', 1),
('0109', 'San Pedro Sacatepéquez', 1),
('0110', 'San Juan Sacatepéquez', 1),
('0111', 'San Raimundo', 1),
('0112', 'Chuarrancho', 1),
('0113', 'Fraijanes', 1),
('0114', 'Amatitlán', 1),
('0115', 'Villa Nueva', 1),
('0116', 'Villa Canales', 1),
('0117', 'Petapa', 1),
('0201', 'Guastatoya', 2),
('0202', 'Morazán', 2),
('0203', 'San Agustín Acasaguastlán', 2),
('0204', 'San Cristobal Acasaguastlán', 2),
('0205', 'El Jícaro', 2),
('0206', 'Sansare', 2),
('0207', 'Sanarate', 2),
('0208', 'San Antonio La Paz', 2),
('0301', 'Antigua Guatemala', 3),
('0302', 'Jocotenango', 3),
('0303', 'Pastores', 3),
('0304', 'Sumpango', 3),
('0305', 'Santo Domingo Xenacoj', 3),
('0306', 'Santiago Sacatepéquez', 3),
('0307', 'San Bartolomé Milpas Altas', 3),
('0308', 'San Lucas Sacatepéquez', 3),
('0309', 'Santa Lucia Milpas Altas', 3),
('0310', 'Magdalena Milpas Altas', 3),
('0311', 'Santa María de Jesus', 3),
('0312', 'Ciudad Vieja', 3),
('0313', 'San Miguel Dueñas', 3),
('0314', 'Alotenango', 3),
('0315', 'San Antonio Aguas Calientes', 3),
('0316', 'Santa Catalina Barahona', 3),
('0401', 'Chimaltenango', 4),
('0402', 'San José Poaquil', 4),
('0403', 'San MartÍn Jilotepeque', 4),
('0404', 'Comalapa', 4),
('0405', 'Santa Apolonia', 4),
('0406', 'Tecpan Guatemala', 4),
('0407', 'Patzun', 4),
('0408', 'Pochuta', 4),
('0409', 'Patzicía', 4),
('0410', 'Santa Cruz Balanya', 4),
('0411', 'Acatenango', 4),
('0412', 'Yepocapa', 4),
('0413', 'San Andrés Itzapa', 4),
('0414', 'Parramos', 4),
('0415', 'Zaragoza', 4),
('0416', 'El Tejar', 4),
('0501', 'Escuintla', 5),
('0502', 'Santa Lucía Cotzumalguapa', 5),
('0503', 'La Democracia', 5),
('0504', 'Siquinala', 5),
('0505', 'Masagua', 5),
('0506', 'Tiquisate', 5),
('0507', 'La Gomera', 5),
('0508', 'Guanagazapa', 5),
('0509', 'San José', 5),
('0510', 'Iztapa', 5),
('0511', 'Palín', 5),
('0512', 'San Vicente Pacaya', 5),
('0513', 'Nueva Concepción', 5),
('0601', 'Cuilapa', 6),
('0602', 'Barberena', 6),
('0603', 'Santa Rosa De Lima', 6),
('0604', 'Casillas', 6),
('0605', 'San Rafael Las Flores', 6),
('0606', 'Oratorio', 6),
('0607', 'San Juan Tecuaco', 6),
('0608', 'Chiquimulilla', 6),
('0609', 'Taxisco', 6),
('0610', 'Santa María Ixhuatan', 6),
('0611', 'Guazacapan', 6),
('0612', 'Santa Cruz Naranjo', 6),
('0613', 'Pueblo Nuevo Viñas', 6),
('0614', 'Nueva Santa Rosa', 6),
('0701', 'Sololá', 7),
('0702', 'San José Chacayá', 7),
('0703', 'Santa María Visitación', 7),
('0704', 'Santa Lucía Utatlan', 7),
('0705', 'Nahuala', 7),
('0706', 'Santa Catarina Ixtahuacan', 7),
('0707', 'Santa Clara La Laguna', 7),
('0708', 'Concepción', 7),
('0709', 'San Andrés Semetabaj', 7),
('0710', 'Panajachel', 7),
('0711', 'Santa Catarina Palopó', 7),
('0712', 'San Antonio Palopó', 7),
('0713', 'San Lucas Tolimán', 7),
('0714', 'Santa Cruz La Laguna', 7),
('0715', 'San Pablo La Laguna', 7),
('0716', 'San Marcos La Laguna', 7),
('0717', 'San Juan La Laguna', 7),
('0718', 'San Pedro La Laguna', 7),
('0719', 'Santiago Atitlán', 7),
('0801', 'Totonicapán', 8),
('0802', 'San Cristóbal Totonicapan', 8),
('0803', 'San Francisco El Alto', 8),
('0804', 'San Andrés Xecul', 8),
('0805', 'Momostenango', 8),
('0806', 'Santa María Chiquimula', 8),
('0807', 'Santa Lucía La Reforma', 8),
('0808', 'San Bartolo', 8),
('0901', 'Quetzaltenango', 9),
('0902', 'Salcajá', 9),
('0903', 'Olintepeque', 9),
('0904', 'San Carlos Sija', 9),
('0905', 'Sibilia', 9),
('0906', 'Cabricán', 9),
('0907', 'Cajolá', 9),
('0908', 'San Miguel Siguilá', 9),
('0909', 'Ostuncalco', 9),
('0910', 'San Mateo', 9),
('0911', 'Concepción Chiquirichapa', 9),
('0912', 'San Martín Sacatepéquez', 9),
('0913', 'Almolonga', 9),
('0914', 'Cantel', 9),
('0915', 'Huitán', 9),
('0916', 'Zunil', 9),
('0917', 'Colomba', 9),
('0918', 'San Francisco La Unión', 9),
('0919', 'El Palmar', 9),
('0920', 'Coatepeque', 9),
('0921', 'Génova', 9),
('0922', 'Flores Costa Cuca', 9),
('0923', 'La Esperanza', 9),
('0924', 'Palestina de Los Altos', 9),
('1001', 'Mazatenango', 10),
('1002', 'Cuyotenango', 10),
('1003', 'San Francisco Zapotitlán', 10),
('1004', 'San Bernardino', 10),
('1005', 'San José El Idolo', 10),
('1006', 'Santo Domingo Suchitepéquez', 10),
('1007', 'San Lorenzo', 10),
('1008', 'Samayac', 10),
('1009', 'San Pablo Jocopilas', 10),
('1010', 'San Antonio Suchitepéquez', 10),
('1011', 'San Miguel Panán', 10),
('1012', 'San Gabriel', 10),
('1013', 'Chicacao', 10),
('1014', 'Patulul', 10),
('1015', 'Santa Barbara', 10),
('1016', 'San Juan Bautista', 10),
('1017', 'Santo Tomßs La Unión', 10),
('1018', 'Zunilito', 10),
('1019', 'Pueblo Nuevo', 10),
('1020', 'Río Bravo', 10),
('1101', 'Retalhuleu', 11),
('1102', 'San Sebastián', 11),
('1103', 'Santa Cruz Muluá', 11),
('1104', 'San Martín Zapotitlán', 11),
('1105', 'San Felipe', 11),
('1106', 'San Andrés Villa Seca', 11),
('1107', 'Champerico', 11),
('1108', 'Nuevo San Carlos', 11),
('1109', 'El Asintal', 11),
('1201', 'San Marcos', 12),
('1202', 'San Pedro Sacatepéquez', 12),
('1203', 'San Antonio Sacatepéquez', 12),
('1204', 'Comitancillo', 12),
('1205', 'San Miguel Ixtahuacán', 12),
('1206', 'Concepción Tutuapa', 12),
('1207', 'Tacaná', 12),
('1208', 'Sibinal', 12),
('1209', 'Tajumulco', 12),
('1210', 'Tejutla', 12),
('1211', 'San Rafael Pie De La Cuesta', 12),
('1212', 'Nuevo Progreso', 12),
('1213', 'El Tumbador', 12),
('1214', 'El Rodeo', 12),
('1215', 'Malacatán', 12),
('1216', 'Catarina', 12),
('1217', 'Ayutla', 12),
('1218', 'Ocós', 12),
('1219', 'San Pablo', 12),
('1220', 'El Quetzal', 12),
('1221', 'La Reforma', 12),
('1222', 'Pajapita', 12),
('1223', 'Ixchiguán', 12),
('1224', 'San José Ojetenam', 12),
('1225', 'San Cristóbal Cucho', 12),
('1226', 'Sipacapa', 12),
('1227', 'Esquipulas Palo Gordo', 12),
('1228', 'Río Blanco', 12),
('1229', 'San Lorenzo', 12),
('1301', 'Huehuetenango', 13),
('1302', 'Chiantla', 13),
('1303', 'Malacatancito', 13),
('1304', 'Cuilco', 13),
('1305', 'Nentón', 13),
('1306', 'San Pedro Necta', 13),
('1307', 'Jacaltenango', 13),
('1308', 'Soloma', 13),
('1309', 'Ixtahuacán', 13),
('1310', 'Santa Bárbara', 13),
('1311', 'La Libertad', 13),
('1312', 'La Democracia', 13),
('1313', 'San Miguel Acatán', 13),
('1314', 'San Rafael La Independencia', 13),
('1315', 'Todos Santos Cuchumatánes', 13),
('1316', 'San Juan Atitán', 13),
('1317', 'Santa Eulalia', 13),
('1318', 'San Mateo Ixtatán', 13),
('1319', 'Colotenango', 13),
('1320', 'San Sebastián Huehetenango', 13),
('1321', 'Tectitán', 13),
('1322', 'Concepción Huista', 13),
('1323', 'San Juan Ixcoy', 13),
('1324', 'San Antonio Huista', 13),
('1325', 'San Sebastián Coatán', 13),
('1326', 'Barillas', 13),
('1327', 'Aguacatán', 13),
('1328', 'San Rafael Petzal', 13),
('1329', 'San Gaspar Ixchil', 13),
('1330', 'Santiago Chimaltenango', 13),
('1331', 'Santa Ana Huista', 13),
('1401', 'Santa Cruz del Quiché', 14),
('1402', 'Chiché', 14),
('1403', 'Chinique', 14),
('1404', 'Zacualpa', 14),
('1405', 'Chajul', 14),
('1406', 'Chichicastenango', 14),
('1407', 'Patzité', 14),
('1408', 'San Antonio Ilotenango', 14),
('1409', 'San Pedro Jocopilas', 14),
('1410', 'Cunén', 14),
('1411', 'San Juan Cotzal', 14),
('1412', 'Joyabaj', 14),
('1413', 'Nebaj', 14),
('1414', 'San Andrés Sajcabajá', 14),
('1415', 'Uspantán', 14),
('1416', 'Sacapulas', 14),
('1417', 'San Bartolomé Jocotenango', 14),
('1418', 'Canillá', 14),
('1419', 'Chicamán', 14),
('1420', 'Ixcán', 14),
('1421', 'Pachalum', 14),
('1501', 'Salamá', 15),
('1502', 'San Miguel Chicaj', 15),
('1503', 'Rabinal', 15),
('1504', 'Cubulco', 15),
('1505', 'Granados', 15),
('1506', 'El Chol', 15),
('1507', 'San Jerónimo', 15),
('1508', 'Purula', 15),
('1601', 'Cobán', 16),
('1602', 'Santa Cruz Verapaz', 16),
('1603', 'San Cristóbal Verapaz', 16),
('1604', 'Tactic', 16),
('1605', 'Tamahù', 16),
('1606', 'Tucurú', 16),
('1607', 'Panzós', 16),
('1608', 'Senahú', 16),
('1609', 'San Pedro Carchá', 16),
('1610', 'San Juan Chamelco', 16),
('1611', 'Lanquín', 16),
('1612', 'Cahabón', 16),
('1613', 'Chisec', 16),
('1614', 'Chahal', 16),
('1615', 'Fray Bartolomé de Las Casas', 16),
('1701', 'Flores', 17),
('1702', 'San José', 17),
('1703', 'San Benito', 17),
('1704', 'San Andrés', 17),
('1705', 'La Libertad', 17),
('1706', 'San Francisco', 17),
('1707', 'Santa Ana', 17),
('1708', 'Dolores', 17),
('1709', 'San Luis', 17),
('1710', 'Sayaxché', 17),
('1711', 'Melchor De Mencos', 17),
('1712', 'Poptún', 17),
('1801', 'Puerto Barrios', 18),
('1802', 'Lívingston', 18),
('1803', 'El Estor', 18),
('1804', 'Morales', 18),
('1805', 'Los Amates', 18),
('1901', 'Zacapa', 19),
('1902', 'Estanzuela', 19),
('1903', 'Río Hondo', 19),
('1904', 'Gualán', 19),
('1905', 'Teculután', 19),
('1906', 'Usumatlán', 19),
('1907', 'Cabañas', 19),
('1908', 'San Diego', 19),
('1909', 'La Unión', 19),
('1910', 'Huité', 19),
('2001', 'Chiquimula', 20),
('2002', 'San José La Arada', 20),
('2003', 'San Juan Ermita', 20),
('2004', 'Jocotán', 20),
('2005', 'Camotán', 20),
('2006', 'Olopa', 20),
('2007', 'Esquipulas', 20),
('2008', 'Concepción Las Minas', 20),
('2009', 'Quetzaltepeque', 20),
('2010', 'San Jacinto', 20),
('2011', 'Ipala', 20),
('2101', 'Jalapa', 21),
('2102', 'San Pedro Pinula', 21),
('2103', 'San Luis Jilotepeque', 21),
('2104', 'San Manuel Chaparrón', 21),
('2105', 'San Carlos Alzatate', 21),
('2106', 'Monjas', 21),
('2107', 'Mataquescuintla', 21),
('2201', 'Jutiapa', 22),
('2202', 'El Progreso', 22),
('2203', 'Santa Catarina Mita', 22),
('2204', 'Agua Blanca', 22),
('2205', 'Asunción Mita', 22),
('2206', 'Yupiltepeque', 22),
('2207', 'Atescatempa', 22),
('2208', 'Jerez', 22),
('2209', 'El Adelanto', 22),
('2210', 'Zapotitlán', 22),
('2211', 'Comapa', 22),
('2212', 'Jalpatagua', 22),
('2213', 'Conguaco', 22),
('2214', 'Moyuta', 22),
('2215', 'Pasaco', 22),
('2216', 'San José Acatempa', 22),
('2217', 'Quesada', 22);

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
('01', 'Occidental');

INSERT INTO [tipos_prestamos] ([codigo], [nombre]) VALUES 
('01', 'Tipo Prestamo 1');

INSERT INTO [puestos] ([codigo], [nombre]) VALUES 
('01', 'Alcalde');

INSERT INTO [bancos] ([codigo], [nombre], [siglas]) VALUES 
('01', 'Banco Banrural', 'BRN');

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
('Seguridad', NULL, 'fad fa-shield-check', 10),
('Aportes', 'aportes', 'fad fa-coins', 4);

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