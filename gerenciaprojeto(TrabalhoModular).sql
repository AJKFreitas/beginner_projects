-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 16-Nov-2016 às 18:05
-- Versão do servidor: 10.1.13-MariaDB
-- PHP Version: 5.5.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gerenciaprojeto`
--
CREATE DATABASE IF NOT EXISTS `gerenciaprojeto` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gerenciaprojeto`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `departamento`
--

CREATE TABLE IF NOT EXISTS `departamento` (
  `codDepartamento` int(5) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  PRIMARY KEY (`codDepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `departamento`
--

INSERT INTO `departamento` (`codDepartamento`, `nome`, `telefone`) VALUES
(1, 'Vendas', '32121331'),
(2, 'Ouvidoria', '32121332'),
(3, 'Financeiro', '32121333'),
(4, 'Implantacao', '32121334'),
(5, 'Treinamento', '32121335'),
(6, 'Suporte', '32121336'),
(7, 'Desenvolvimento', '32121337'),
(8, 'Administracao', '32121338');

-- --------------------------------------------------------

--
-- Estrutura da tabela `enderecofuncionario`
--

CREATE TABLE IF NOT EXISTS `enderecofuncionario` (
  `matriculaFuncionario` int(5) NOT NULL,
  `logradouro` varchar(255) NOT NULL,
  `numero` int(10) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `cep` varchar(9) NOT NULL,
  KEY `FK_Endereco_Funcionario` (`matriculaFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `enderecofuncionario`
--

INSERT INTO `enderecofuncionario` (`matriculaFuncionario`, `logradouro`, `numero`, `bairro`, `cidade`, `uf`, `cep`) VALUES
(999, 'Rua Jeronimo Norberto Fernandes', 30, 'Benfica', 'Juiz de Fora', 'MG', '33200-000'),
(163, 'Rua Jeronimo Norberto Fernandes', 30, 'Benfica', 'Juiz de Fora', 'MG', '36090-513');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE IF NOT EXISTS `funcionario` (
  `matricula` int(5) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `formAcademica` varchar(20) DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `codDepartamento` int(5) NOT NULL,
  `cargaHoraria` int(2) DEFAULT NULL,
  `funcao` varchar(10) NOT NULL,
  `login` varchar(40) NOT NULL,
  `senha` varchar(70) NOT NULL,
  PRIMARY KEY (`matricula`),
  KEY `FK_Funcionario_Departamento` (`codDepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`matricula`, `nome`, `cpf`, `telefone`, `formAcademica`, `area`, `codDepartamento`, `cargaHoraria`, `funcao`, `login`, `senha`) VALUES
(163, 'Paulo Donizeth', '105.617.106-51', '(32) 8839-9958', 'null', 'null', 7, NULL, 'Diretor', 'paulo.junior', 'pdaj93'),
(164, 'Simara Laura Salgado', NULL, NULL, NULL, NULL, 8, NULL, 'Diretor', 'simara.salgado', 'simara123'),
(165, 'Otavio Abdallah', NULL, NULL, NULL, NULL, 3, NULL, 'Gerente', 'otavio.abdallah', '123456'),
(999, 'teste', '321.321.321-31', NULL, 'Fundamental', 'RH', 2, NULL, 'Analista', 'teste', '123');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario_projeto`
--

CREATE TABLE IF NOT EXISTS `funcionario_projeto` (
  `matricula` int(5) NOT NULL,
  `codProjeto` int(5) NOT NULL,
  `funcao` varchar(20) NOT NULL,
  `cargaHoraria` int(2) NOT NULL,
  `dataInicio` varchar(20) NOT NULL,
  `dataTermino` varchar(20) NOT NULL,
  `observacao` varchar(255) NOT NULL,
  KEY `matricula` (`matricula`),
  KEY `codProjeto` (`codProjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `projeto`
--

CREATE TABLE IF NOT EXISTS `projeto` (
  `codProjeto` int(5) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `codDepartamento` int(5) NOT NULL,
  `cliente` varchar(70) NOT NULL,
  `telCliente` varchar(16) NOT NULL,
  `dataInicio` varchar(10) NOT NULL,
  `dataTermino` varchar(10) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`codProjeto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `projeto`
--

INSERT INTO `projeto` (`codProjeto`, `nome`, `descricao`, `codDepartamento`, `cliente`, `telCliente`, `dataInicio`, `dataTermino`, `status`) VALUES
(1, 'Aplicacao Mobile', 'Aplicativo que os clientes da Loja Magnos ira fazer os seus pedidos.', 3, 'Carlos Magno', '032 9985-8574', '07/11/2016', '28/11/2016', 'Negociacao'),
(2, 'Controle Financeiro', 'Sistema que sera capaz de cadastrar entrada e saida de valores.', 7, 'Julio Cesar', '032 8852-8993', '14/11/2016', '28/11/2016', 'Entregue'),
(3, 'Site Responsivo', 'Site responsivo para a empresa Doces e Doces', 6, 'Pedro Carlos', '032 3214-8574', '02/12/2016', '27/12/2016', 'Desenvolvimento'),
(4, 'Controle de Ponto', 'Criar Sistema de controle de ponto para o evento CAT', 5, 'Pedro Carlos', '032 3214-8574', '09/12/2016', '20/12/2016', 'Cancelado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `projeto_observado`
--

CREATE TABLE IF NOT EXISTS `projeto_observado` (
  `id_projeto_observado` int(5) NOT NULL,
  `codProjeto` int(5) NOT NULL,
  `codDepartamento` int(5) NOT NULL,
  `notificacao` varchar(255) NOT NULL,
  `lido` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_projeto_observado`),
  KEY `codProjeto` (`codProjeto`),
  KEY `codDepartamento` (`codDepartamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `projeto_observado`
--

INSERT INTO `projeto_observado` (`id_projeto_observado`, `codProjeto`, `codDepartamento`, `notificacao`, `lido`) VALUES
(14, 4, 7, '<strong>Atenção!</strong> O projeto <strong>Controle de Ponto</strong> foi alocado ao departamento <strong>Desenvolvimento</strong>!', 0),
(15, 3, 7, '<strong>Atenção!</strong> O projeto <strong>Site Responsivo</strong> foi alocado ao departamento <strong>Desenvolvimento</strong>!', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `telefonefuncionario`
--

CREATE TABLE IF NOT EXISTS `telefonefuncionario` (
  `matriculaFuncionario` int(10) NOT NULL,
  `numTelefone` varchar(20) NOT NULL,
  KEY `matriculaFuncionario` (`matriculaFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `enderecofuncionario`
--
ALTER TABLE `enderecofuncionario`
  ADD CONSTRAINT `FK_Endereco_Funcionario` FOREIGN KEY (`matriculaFuncionario`) REFERENCES `funcionario` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `FK_Funcionario_Departamento` FOREIGN KEY (`codDepartamento`) REFERENCES `departamento` (`codDepartamento`) ON UPDATE CASCADE;

--
-- Limitadores para a tabela `funcionario_projeto`
--
ALTER TABLE `funcionario_projeto`
  ADD CONSTRAINT `FK_Funcionario` FOREIGN KEY (`matricula`) REFERENCES `funcionario` (`matricula`) ON UPDATE CASCADE;

--
-- Limitadores para a tabela `projeto_observado`
--
ALTER TABLE `projeto_observado`
  ADD CONSTRAINT `FK_Departamento` FOREIGN KEY (`codDepartamento`) REFERENCES `departamento` (`codDepartamento`),
  ADD CONSTRAINT `FK_Projeto` FOREIGN KEY (`codProjeto`) REFERENCES `projeto` (`codProjeto`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
