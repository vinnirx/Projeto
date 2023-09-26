CREATE DATABASE frete_db;
USE frete_db;

-- Tabela Produto
CREATE TABLE Produto (
    SKU BIGINT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Peso DECIMAL(10,2) NOT NULL,
    Comprimento DECIMAL(10,2),
    Largura DECIMAL(10,2),
    Altura DECIMAL(10,2),
    Descricao TEXT
);

-- Tabela Pedido
CREATE TABLE Pedido (
    ID_Pedido BIGINT AUTO_INCREMENT PRIMARY KEY,
    DataPedido DATETIME NOT NULL,
    CEP_Entrega VARCHAR(9)
);

-- Tabela Pedido_Produto (Relacionamento Muitos para Muitos entre Produto e Pedido)
CREATE TABLE Pedido_Produto (
    ID_Pedido BIGINT,
    SKU BIGINT,
    Quantidade INT NOT NULL,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedido(ID_Pedido),
    FOREIGN KEY (SKU) REFERENCES Produto(SKU),
    PRIMARY KEY (ID_Pedido, SKU)
);

-- Tabela Estoque
CREATE TABLE Estoque (
    SKU BIGINT PRIMARY KEY,
    Disponibilidade BOOLEAN NOT NULL,
    TempoExpedicao INT,
    FOREIGN KEY (SKU) REFERENCES Produto(SKU)
);

-- Tabela CEP
CREATE TABLE CEP (
    CEP VARCHAR(9) PRIMARY KEY,
    Cidade VARCHAR(255) NOT NULL,
    Estado VARCHAR(2) NOT NULL,
    Regiao VARCHAR(255) NOT NULL
);
