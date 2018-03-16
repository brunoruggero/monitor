DROP TABLE equipamento;
DROP TABLE usuario;
DROP TABLE role_users;
DROP TABLE estadoEquipamento;

CREATE TABLE usuario(
idUsuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nomeUsuario VARCHAR (50),
user VARCHAR (10),
pass VARCHAR (10),
role VARCHAR (5)
/*FOREIGN KEY (roleFK) REFERENCES role_users(role_name)*/
)Engine = InnoDB;

CREATE TABLE role_users(
role_name VARCHAR(5) PRIMARY KEY NOT NULL
)Engine = InnoDB;

CREATE TABLE equipamento(
idEquipamento INT PRIMARY KEY AUTO_INCREMENT,
nomeEquipamento VARCHAR(30),
grupoTrabalho VARCHAR(30),
setor VARCHAR(30),
enderecoIP VARCHAR(15),
maskRede VARCHAR(15),
gatewayPadrao VARCHAR(15),
dnsPrimario VARCHAR(15),
dnsSecundario VARCHAR(15),
dataCadastro date
)Engine = InnoDB;

CREATE TABLE estadoEquipamento(
idEstado INT PRIMARY KEY AUTO_INCREMENT,
nomeEstado VARCHAR(30) unique,
ipEquipamento VARCHAR(15),
estadoEqui VARCHAR(10), 
dataEstado datetime
)Engine = InnoDB;

/* Criando uma constraint para tabela de Equipamento */
ALTER TABLE equipamento add constraint nomeEquip_uniq unique (nomeEquipamento);
ALTER TABLE equipamento add constraint endIP_uniq unique (enderecoIP);

/* Criando uma constraint para tabela de Usuario */
ALTER TABLE usuario add constraint user_uniq unique (user);

SELECT * FROM equipamento;
SELECT * FROM usuario;
SELECT * FROM estadoEquipamento;

DELETE FROM equipamento WHERE idEquipamento = 3;

insert into usuario (nomeUsuario, user, pass) values ('Administrador', 'admin', 'admin123');
insert into usuario (nomeUsuario, user, pass) values ('Bruno Ruggero', 'ruggero', 'admin123');
