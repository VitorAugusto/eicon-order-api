CREATE DATABASE order_api;

CREATE TABLE IF NOT EXISTS order_api.cliente (
                                                 `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                 `nome` TEXT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS order_api.pedido (
                                                `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                `numero_controle` VARCHAR(30) NOT NULL,
    `data_cadastro` DATETIME NOT NULL,
    `nome` VARCHAR(50) NOT NULL,
    `valor` BIGINT NOT NULL,
    `quantidade` BIGINT NOT NULL,
    `valor_total` BIGINT NOT NULL,
    `codigo_cliente` BIGINT NOT NULL,
    FOREIGN KEY (codigo_cliente) REFERENCES cliente(id)
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

insert into order_api.cliente (id, nome)
values (1, "Cliente_1");

insert into order_api.cliente (id, nome)
values (2, "Cliente_2");

insert into order_api.cliente (id, nome)
values (3, "Cliente_3");

insert into order_api.cliente (id, nome)
values (4, "Cliente_4");

insert into order_api.cliente (id, nome)
values (5, "Cliente_5");

insert into order_api.cliente (id, nome)
values (6, "Cliente_6");

insert into order_api.cliente (id, nome)
values (7, "Cliente_7");

insert into order_api.cliente (id, nome)
values (8, "Cliente_8");

insert into order_api.cliente (id, nome)
values (9, "Cliente_9");

insert into order_api.cliente (id, nome)
values (10, "Cliente_10");