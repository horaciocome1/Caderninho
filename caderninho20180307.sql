/* Este script cria o banco e a tabela produtos
   20180307*/
   
create database if not exists `caderninho`;
use `caderninho`;

drop table if exists `produtos`;
create table `produtos` (
	`id` int not null unique auto_increment,
	`nome` varchar(50) unique not null,
	`desc` varchar(135),
	`custo` decimal(6,2) not null,
	`preco` decimal(6,2) not null,
	primary key(`id`)
);