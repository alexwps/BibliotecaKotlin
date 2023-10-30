CREATE TABLE `biblioteca` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `nome` varchar(255) NOT NULL,
 `endereco` varchar(255) NOT NULL,
 `funcionamento` varchar(255) NOT NULL,
 `observacao` varchar(255) DEFAULT NULL
);