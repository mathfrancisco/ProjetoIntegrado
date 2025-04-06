# Realizador por : Matheus Francisco RA 24001882

# Sistema de Gestão para Microempresa de Estética

## Introdução

Este projeto visa desenvolver um sistema de gestão para microempresas de estética, utilizando os princípios da Programação Orientada a Objetos (POO) e modelagem de banco de dados. O objetivo é criar uma solução robusta e escalável que atenda às necessidades específicas do setor de estética.

## Objetivos do Projeto

- **Desenvolvimento de Classes**: Criar classes que representem as entidades do negócio, aplicando os princípios de POO: abstração, encapsulamento, herança e polimorfismo.
- **Modelagem de Banco de Dados**: Desenvolver um esquema de banco de dados relacional, utilizando diagramas Entidade-Relacionamento (DER) e scripts SQL para garantir integridade referencial.
- **Gestão de Estoque**: Implementar funcionalidades para controle de estoque de produtos utilizados nos serviços.
- **Controle models.Financeiro**: Criar um módulo de controle financeiro para registrar receitas e despesas.
- **Emissão de Relatórios**: Desenvolver relatórios financeiros e de desempenho dos profissionais e serviços.

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Banco de Dados**: MySQL ou Docker(Opcional para conteinerização do banco de dados) 

## Estrutura do Projeto

```
/SistemaGestaoEstetica
│── src/
│   ├── Main.java
│   ├── models/
│   │   ├── models.Cliente.java
│   │   ├── models.Profissional.java
│   │   ├── models.Servico.java
│   │   ├── Agendamento.java
│   │   ├── models.Produto.java
│   │   ├── models.Financeiro.java
│   ├── database/
│   │   ├── database.DatabaseConnection.java
│   ├── dao/
│   │   ├── dao.ClienteDAO.java
│   │   ├── dao.ProfissionalDAO.java
│   │   ├── dao.ServicoDAO.java
│   │   ├── dao.AgendamentoDAO.java
│   │   ├── dao.ProdutoDAO.java
│   │   ├── dao.FinanceiroDAO.java
│   ├── utils/
│   │   ├── InputHelper.java
│   ├── services/
│   │   ├── services.AgendamentoService.java
│   │   ├── services.FinanceiroService.java
│── init.sql
│── README.md
```

## Diagrama de Classes

### Representação das Entidades no Sistema

```erDiagram
title Diagrama de Classes do Sistema de Gestão de Estética
classDiagram
    class models.Cliente {
        +String nome
        +String telefone
        +getNome()
        +getTelefone()
    }
    
    class models.Profissional {
        +String nome
        +String especialidade
        +getNome()
        +getEspecialidade()
    }
    
    class models.Servico {
        +String descricao
        +double preco
        +getDescricao()
        +getPreco()
    }
    
    class Agendamento {
        +models.Cliente cliente
        +models.Profissional profissional
        +models.Servico servico
        +Date data
        +agendarServico()
    }
    
    class models.Produto {
        +String nome
        +int quantidade
        +double preco
        +getNome()
        +getQuantidade()
        +getPreco()
    }

    class models.Financeiro {
        +double receita
        +double despesa
        +double calcularSaldo()
    }

    models.Cliente --> Agendamento
    models.Profissional --> Agendamento
    models.Servico --> Agendamento
    models.Servico --> models.Produto
    models.Financeiro --> models.Servico
    models.Financeiro --> models.Produto
```

## Modelagem de Banco de Dados

### Diagrama Entidade-Relacionamento (DER)

```erDiagram
erDiagram
    CLIENTE {
        INT id_cliente PK AI
        VARCHAR(100) nome
        VARCHAR(14) cpf
        VARCHAR(15) telefone
        VARCHAR(100) email
        VARCHAR(200) endereco
        DATE data_nascimento
        DATE data_cadastro
    }

    PROFISSIONAL {
        INT id_profissional PK AI
        VARCHAR(100) nome
        VARCHAR(14) cpf
        VARCHAR(15) telefone
        VARCHAR(100) email
        VARCHAR(200) endereco
        DATE data_nascimento
        VARCHAR(50) especialidade
    }

    SERVICO {
        INT id_servico PK AI
        VARCHAR(100) nome
        TEXT descricao
        DECIMAL(10,2) valor
        INT duracao_minutos
        ENUM('FACIAL', 'CORPORAL') tipo
    }

    PRODUTO {
        INT id_produto PK AI
        VARCHAR(100) nome
        INT quantidade
        DECIMAL(10,2) preco
    }

    FINANCEIRO {
        INT id_financeiro PK AI
        DECIMAL(10,2) receita
        DECIMAL(10,2) despesa
        DECIMAL(10,2) saldo
    }

    AGENDAMENTO {
        INT id_agendamento PK AI
        INT id_cliente FK
        INT id_profissional FK
        INT id_servico FK
        DATE data
        TIME horario
        ENUM('AGENDADO', 'CONFIRMADO', 'REALIZADO', 'CANCELADO') status
    }

    CLIENTE ||--o{ AGENDAMENTO : "1:N"
    PROFISSIONAL ||--o{ AGENDAMENTO : "1:N"
    SERVICO ||--o{ AGENDAMENTO : "1:N"
    SERVICO ||--o{ PRODUTO : "1:N"
    FINANCEIRO ||--o{ SERVICO : "1:N"
    FINANCEIRO ||--o{ PRODUTO : "1:N"
```

## Script SQL de Criação do Banco de Dados

```sql
CREATE TABLE models.Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE models.Profissional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

CREATE TABLE models.Servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE models.Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE models.Financeiro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    receita DECIMAL(10,2) NOT NULL,
    despesa DECIMAL(10,2) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL
);

CREATE TABLE Agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    profissional_id INT,
    servico_id INT,
    data DATETIME NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES models.Cliente(id),
    FOREIGN KEY (profissional_id) REFERENCES models.Profissional(id),
    FOREIGN KEY (servico_id) REFERENCES models.Servico(id)
);
```

## Benefícios do Projeto

- **Gestão Eficiente**: Automatiza o agendamento, controle de estoque e fluxo financeiro.
- **Melhoria no Atendimento**: Permite um atendimento personalizado com fichas técnicas detalhadas.
- **Monitoramento models.Financeiro**: Acompanha receitas, despesas e calcula saldo para melhor tomada de decisão.
- **Escalabilidade**: A modelagem POO e o banco de dados relacional garantem que o sistema cresça junto com a empresa.

## Pré-requisitos

- **Java 11 ou superior**
- **Docker (opcional, para configuração do banco de dados)**
- **MySQL 8.0**

## Configuração do Ambiente
1. Clonar o Repositório
   ```bash 
   git clone https://github.com/seu-usuario/sistema-gestao-estetica.git 
   cd sistema-gestao-estetica ```
2. Configuração do Banco de Dados
   Opção 1: Usando Docker

## Instale o Docker e Docker Compose
## Execute o comando:

```bash
  Copiar docker-compose up -d
Isso irá:

Iniciar um container MySQL
Criar o banco de dados sistema_gestao_estetica
Configurar usuário e senha
Criar as tabelas iniciais
Iniciar o phpMyAdmin na porta 8080

Acesse o phpMyAdmin:

URL: http://localhost:8080
Usuário: estetica_user
Senha: estetica_password

Opção 2: Configuração Manual

Crie um banco de dados MySQL
Execute o script init.sql para criar as tabelas
Atualize as configurações de conexão no arquivo DatabaseConnection.java

3. Compilar o Projeto
   bashCopiarmvn clean install
4. Executar a Aplicação
   bashCopiarmvn exec:java -Dexec.mainClass="Main"
   Estrutura do Projeto
   Copiar/SistemaGestaoEstetica
   │── src/
   │   ├── Main.java
   │   ├── models/
   │   ├── database/
   │   ├── dao/
   │   ├── services/
   │── init.sql
   │── pom.xml
   │── README.md
   ```
## Funcionalidades do Menu
## Menu Principal

- **Gerenciar Agendamentos**
- **Gerenciar Financeiro**
- **Cadastrar Cliente**
- **Cadastrar Profissional**
- **Cadastrar Serviço**
- **Sair**

## Submenu de Agendamentos

- **Criar Agendamento**
- **Listar Agendamentos do Cliente**
- **Atualizar Status do Agendamento**

## Submenu Financeiro

- **Registrar Lançamento Financeiro**
- **Gerar Relatório Financeiro**
- **Obter Resumo Financeiro**

