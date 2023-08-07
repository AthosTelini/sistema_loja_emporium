-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.4.27-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para bdjava
DROP DATABASE IF EXISTS `bdjava`;
CREATE DATABASE IF NOT EXISTS `bdjava` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `bdjava`;

-- Copiando estrutura para tabela bdjava.cidade
DROP TABLE IF EXISTS `cidade`;
CREATE TABLE IF NOT EXISTS `cidade` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCidade` varchar(50) NOT NULL DEFAULT '0',
  `uf` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela bdjava.cidade: ~7 rows (aproximadamente)
INSERT INTO `cidade` (`codigo`, `nomeCidade`, `uf`) VALUES
	(1, 'Machado', 'Mg'),
	(2, 'São Paulo', 'Sp'),
	(3, 'Alfenas', 'Mg'),
	(4, 'Poço Fundo', 'Mg'),
	(7, 'Rio de Janeiro', 'RJ'),
	(8, 'Uberlândia', 'MG'),
	(9, 'Poço Fundo', 'MG');

-- Copiando estrutura para tabela bdjava.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(200) NOT NULL,
  `estado` varchar(200) NOT NULL,
  `cidade` varchar(200) NOT NULL,
  `bairro` varchar(200) NOT NULL,
  `rua` varchar(200) NOT NULL,
  `numero` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela bdjava.cliente: ~4 rows (aproximadamente)
INSERT INTO `cliente` (`codigo`, `nomeCliente`, `estado`, `cidade`, `bairro`, `rua`, `numero`, `email`) VALUES
	(1, 'ERENALDO', 'Mg', 'Alfenas', 'Lagoa azul', 'Alecrim', '26', 'Erenaldo@hotmail.com'),
	(2, 'BERENICE', 'Mg', 'Machado', 'Sta', 'Joao sobrinho', '322', 'Romario777@gmail.com'),
	(3, 'ESTEVAM', 'MG', 'Machado', 'Santa Luiza', 'Visconde de Fatima', '26', 'Estevam@men'),
	(4, 'ALAELSOM', 'SP', 'Jundiai', 'CAMPO VERDE', 'Santa Fé', '659', 'alelsom@gmail.com');

-- Copiando estrutura para tabela bdjava.funcionario
DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE IF NOT EXISTS `funcionario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFuncionario` varchar(50) NOT NULL DEFAULT '0',
  `salario` double NOT NULL DEFAULT 0,
  `nascimento` date NOT NULL,
  `senha` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cidade` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`codigo`),
  KEY `FK_funcionario_cidade` (`cidade`),
  CONSTRAINT `FK_funcionario_cidade` FOREIGN KEY (`cidade`) REFERENCES `cidade` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela bdjava.funcionario: ~5 rows (aproximadamente)
INSERT INTO `funcionario` (`codigo`, `nomeFuncionario`, `salario`, `nascimento`, `senha`, `cidade`) VALUES
	(1, 'Jose Alves', 1200, '1966-06-10', '1', 1),
	(2, 'Maria Benedita', 2000, '1980-02-20', '123', 1),
	(3, 'Athur Mezenes', 1680, '1998-10-12', '1234', 1),
	(5, 'Hellen ', 3200, '2003-03-26', '123', 9),
	(14, 'admin', 0, '2000-05-04', '12345', 1);

-- Copiando estrutura para tabela bdjava.itensvenda
DROP TABLE IF EXISTS `itensvenda`;
CREATE TABLE IF NOT EXISTS `itensvenda` (
  `iditensvenda` int(11) NOT NULL AUTO_INCREMENT,
  `idvenda` int(11) DEFAULT NULL,
  `idProduto` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`iditensvenda`) USING BTREE,
  KEY `FK_itensvenda_venda` (`idvenda`) USING BTREE,
  KEY `FK_itensvenda_produto` (`idProduto`) USING BTREE,
  CONSTRAINT `FK_intensvenda_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_itensvenda_venda` FOREIGN KEY (`idvenda`) REFERENCES `venda` (`idvenda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela bdjava.itensvenda: ~23 rows (aproximadamente)
INSERT INTO `itensvenda` (`iditensvenda`, `idvenda`, `idProduto`, `quantidade`, `valor`) VALUES
	(18, 1, 3, 2, 30),
	(19, 12, 1, 1, 152),
	(21, 14, 6, 1, 160),
	(22, 15, 6, 1, 160),
	(23, 16, 1, 1, 152),
	(25, 18, 1, 1, 152),
	(27, 19, 2, 3, 60),
	(28, 20, 1, 5, 152),
	(29, 23, 4, 1, 60),
	(30, 23, 6, 2, 160),
	(31, 24, 1, 3, 120),
	(32, 24, 6, 3, 160),
	(33, 26, 4, 2, 60),
	(35, 36, 4, 1, 60),
	(42, 40, 3, 4, 60),
	(44, 40, 2, 1, 60),
	(45, 41, 3, 1, 63),
	(46, 42, 6, 2, 160),
	(48, 47, 4, 2, 60),
	(49, 47, 6, 1, 160),
	(50, 48, 4, 2, 60),
	(56, 54, 9, 4, 13.800000190734863),
	(60, 56, 10, 1, 48),
	(62, 60, 3, 1, 63),
	(63, 60, 6, 2, 160),
	(64, 60, 9, 1, 13.800000190734863);

-- Copiando estrutura para tabela bdjava.produto
DROP TABLE IF EXISTS `produto`;
CREATE TABLE IF NOT EXISTS `produto` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nomeProduto` varchar(200) NOT NULL,
  `precoCusto` float NOT NULL DEFAULT 0,
  `margemLucro` float NOT NULL,
  `precoVenda` float NOT NULL DEFAULT 0,
  `tamanho` varchar(50) NOT NULL,
  `qtdeEstoque` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela bdjava.produto: ~7 rows (aproximadamente)
INSERT INTO `produto` (`codigo`, `nomeProduto`, `precoCusto`, `margemLucro`, `precoVenda`, `tamanho`, `qtdeEstoque`) VALUES
	(1, 'Calça jogger masculino', 80, 50, 120, 'M', 34),
	(2, 'Moletom canguru feminino', 40, 80, 72, 'P', 2),
	(3, 'Camisa Polo Marine Classic', 30, 110, 63, 'p', 7),
	(4, 'Camiseta slim em algodão', 30, 100, 60, 'M', 26),
	(6, 'Camiseta Masculina Jeans casual', 80, 100, 160, 'M', 3),
	(9, 'Meia nike', 6.9, 100, 13.8, 'M', 4),
	(10, 'SHORT DRY FIT', 40, 110, 84, 'P,M,G', 14);

-- Copiando estrutura para tabela bdjava.venda
DROP TABLE IF EXISTS `venda`;
CREATE TABLE IF NOT EXISTS `venda` (
  `idvenda` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) DEFAULT NULL,
  `dataVenda` date DEFAULT NULL,
  PRIMARY KEY (`idvenda`) USING BTREE,
  KEY `idcliente` (`idcliente`) USING BTREE,
  CONSTRAINT `FK_venda_cliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Copiando dados para a tabela bdjava.venda: ~0 rows (aproximadamente)
INSERT INTO `venda` (`idvenda`, `idcliente`, `dataVenda`) VALUES
	(1, 1, '2023-06-26'),
	(2, 1, '2023-06-26'),
	(4, 3, '2023-06-26'),
	(5, 4, '2023-06-26'),
	(6, 1, '2023-06-26'),
	(7, 1, '2023-06-26'),
	(8, 1, '2023-06-26'),
	(10, 1, '2023-06-26'),
	(12, 1, '2023-06-26'),
	(14, 4, '2023-06-26'),
	(15, 1, '2023-06-26'),
	(16, 1, '2023-06-26'),
	(18, 1, '2023-06-26'),
	(19, 1, '2023-06-26'),
	(20, 2, '2023-06-26'),
	(21, 2, '2023-06-26'),
	(22, 1, '2023-06-26'),
	(23, 3, '2023-06-26'),
	(24, 1, '2023-06-26'),
	(26, 4, '2023-06-26'),
	(27, 1, '2023-06-27'),
	(28, 1, '2023-06-27'),
	(29, 1, '2023-06-27'),
	(30, 1, '2023-06-27'),
	(31, 1, '2023-06-27'),
	(33, 1, '2023-06-27'),
	(36, 1, '2023-06-27'),
	(40, 1, '2023-06-27'),
	(41, 1, '2023-06-27'),
	(42, 1, '2023-06-27'),
	(44, 1, '2023-06-27'),
	(45, 1, '2023-06-27'),
	(47, 2, '2023-06-27'),
	(48, 4, '2023-06-27'),
	(54, 4, '2023-06-28'),
	(56, 4, '2023-07-02'),
	(57, 1, '2023-07-02'),
	(59, 1, '2023-07-07'),
	(60, 1, '2023-08-07'),
	(61, 1, '2023-08-07'),
	(62, 1, '2023-08-07');

-- Copiando estrutura para trigger bdjava.tri_atualizaEstoque
DROP TRIGGER IF EXISTS `tri_atualizaEstoque`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_atualizaEstoque` AFTER UPDATE ON `itensvenda` FOR EACH ROW BEGIN
if (NEW.quantidade > OLD.quantidade)
then UPDATE produto SET qtdeEstoque = qtdeEstoque - (NEW.quantidade - OLD.quantidade)
		WHERE codigo = NEW.idProduto;
ELSE if (NEW.quantidade < OLD.quantidade)
				then UPDATE produto SET qtdeEstoque = qtdeEstoque + (OLD.quantidade - NEW.quantidade)
				WHERE codigo = NEW.idProduto;
				END if;
END if;

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger bdjava.tri_baixaEstoque
DROP TRIGGER IF EXISTS `tri_baixaEstoque`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_baixaEstoque` BEFORE INSERT ON `itensvenda` FOR EACH ROW BEGIN
UPDATE produto SET qtdeEstoque = qtdeEstoque - new.quantidade
WHERE codigo = new.idProduto;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger bdjava.tri_margemLucro
DROP TRIGGER IF EXISTS `tri_margemLucro`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_margemLucro` BEFORE UPDATE ON `produto` FOR EACH ROW BEGIN
if (NEW.margemLucro>0)
	then SET NEW.precoVenda = NEW.precoCusto + (NEW.precoCusto* NEW.margemLucro/100);
	END if;

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Copiando estrutura para trigger bdjava.tri_margemLucroInsert
DROP TRIGGER IF EXISTS `tri_margemLucroInsert`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_margemLucroInsert` BEFORE INSERT ON `produto` FOR EACH ROW BEGIN
if (NEW.margemLucro>0)
	then SET NEW.precoVenda = NEW.precoCusto + (NEW.precoCusto* NEW.margemLucro/100);
	END if;

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
