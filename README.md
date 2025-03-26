# Sistema de Gestão para Microempresa de Estética

## Introdução

Este projeto visa desenvolver um sistema de gestão para microempresas de estética, utilizando os princípios da Programação Orientada a Objetos (POO) e modelagem de banco de dados. O objetivo é criar uma solução robusta e escalável que atenda às necessidades específicas do setor de estética.

## Objetivos do Projeto

- **Desenvolvimento de Classes**: Criar classes que representem as entidades do negócio, aplicando os princípios de POO: abstração, encapsulamento, herança e polimorfismo.
- **Modelagem de Banco de Dados**: Desenvolver um esquema de banco de dados relacional, utilizando diagramas Entidade-Relacionamento (DER) e scripts SQL para garantir integridade referencial.

## Diagrama de Classes

### Representação das Entidades no Sistema

```mermaid
title Diagrama de Classes do Sistema de Gestão de Estética
classDiagram
    class Cliente {
        +String nome
        +String telefone
        +getNome()
        +getTelefone()
    }
    
    class Profissional {
        +String nome
        +String especialidade
        +getNome()
        +getEspecialidade()
    }
    
    class Servico {
        +String descricao
        +double preco
        +getDescricao()
        +getPreco()
    }
    
    class Agendamento {
        +Cliente cliente
        +Profissional profissional
        +Servico servico
        +Date data
        +agendarServico()
    }
    
    Cliente --> Agendamento
    Profissional --> Agendamento
    Servico --> Agendamento
```

## Modelagem de Banco de Dados

### Diagrama Entidade-Relacionamento (DER)

```mermaid
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
```

## Script SQL de Criação do Banco de Dados

```sql
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE Profissional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

CREATE TABLE Servico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

CREATE TABLE Agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    profissional_id INT,
    servico_id INT,
    data DATETIME NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (profissional_id) REFERENCES Profissional(id),
    FOREIGN KEY (servico_id) REFERENCES Servico(id)
);
```

## Benefícios do Projeto

- **Gestão Eficiente**: Automatiza o agendamento e controle de estoque.
- **Melhoria no Atendimento**: Permite um atendimento personalizado com fichas técnicas detalhadas.
- **Escalabilidade**: A modelagem POO e o banco de dados relacional garantem que o sistema cresça junto com a empresa.

## Futuras Melhorias

- **Integração com Pagamentos Online**: Implementar sistemas de pagamento eletrônico.
- **Desenvolvimento de Aplicativo Móvel**: Criar uma interface móvel para clientes e profissionais.
- **Dashboards para Análise de Dados**: Incluir ferramentas de análise para melhorar a tomada de decisões.
