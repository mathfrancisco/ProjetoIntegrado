-- Sistema de Gestão para Microempresa de Estética
-- Script de Criação do Banco de Dados

-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS sistema_gestao_estetica;
USE sistema_gestao_estetica;

-- Tabela de Clientes
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    endereco VARCHAR(200),
    data_nascimento DATE,
    data_cadastro DATE DEFAULT (CURRENT_DATE)
);

-- Tabela de Profissionais
CREATE TABLE Profissional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    endereco VARCHAR(200),
    data_nascimento DATE,
    especialidade VARCHAR(50) NOT NULL
);

-- Tabela de Serviços
CREATE TABLE Servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    valor DECIMAL(10,2) NOT NULL,
    duracao_minutos INT NOT NULL,
    tipo ENUM('FACIAL', 'CORPORAL') NOT NULL
);

-- Tabela de Produtos
CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL DEFAULT 0,
    preco DECIMAL(10,2) NOT NULL
);

-- Tabela de Agendamentos
CREATE TABLE Agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    profissional_id INT,
    servico_id INT,
    data DATETIME NOT NULL,
    horario TIME NOT NULL,
    status ENUM('AGENDADO', 'CONFIRMADO', 'REALIZADO', 'CANCELADO') DEFAULT 'AGENDADO',
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE SET NULL,
    FOREIGN KEY (profissional_id) REFERENCES Profissional(id) ON DELETE SET NULL,
    FOREIGN KEY (servico_id) REFERENCES Servico(id) ON DELETE SET NULL
);

-- Tabela Financeiro
CREATE TABLE Financeiro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    receita DECIMAL(10,2) NOT NULL DEFAULT 0,
    despesa DECIMAL(10,2) NOT NULL DEFAULT 0,
    saldo DECIMAL(10,2) GENERATED ALWAYS AS (receita - despesa) STORED
);

-- Índices para melhorar performance
CREATE INDEX idx_cliente_nome ON Cliente(nome);
CREATE INDEX idx_profissional_nome ON Profissional(nome);
CREATE INDEX idx_servico_nome ON Servico(nome);
CREATE INDEX idx_agendamento_data ON Agendamento(data);

-- Exemplo de inserção de dados iniciais
-- Clientes de exemplo
INSERT INTO Cliente (nome, cpf, telefone, email) VALUES 
('Maria Silva', '123.456.789-00', '(11) 98765-4321', 'maria@email.com'),
('João Santos', '987.654.321-00', '(11) 91234-5678', 'joao@email.com');

-- Profissionais de exemplo
INSERT INTO Profissional (nome, cpf, telefone, especialidade) VALUES 
('Ana Oliveira', '456.789.123-00', '(11) 97654-3210', 'Estética Facial'),
('Carlos Souza', '789.123.456-00', '(11) 92345-6789', 'Massagem Corporal');

-- Serviços de exemplo
INSERT INTO Servico (nome, descricao, valor, duracao_minutos, tipo) VALUES 
('Limpeza de Pele', 'Limpeza profunda com extração', 120.00, 60, 'FACIAL'),
('Massagem Relaxante', 'Massagem completa para alívio de tensões', 150.00, 90, 'CORPORAL');

-- Produtos de exemplo
INSERT INTO Produto (nome, quantidade, preco) VALUES 
('Sérum Facial', 50, 80.00),
('Óleo de Massagem', 30, 45.00);
