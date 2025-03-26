# Desenvolvimento de um Sistema de Gestão para Microempresa de Estética

## 1. Introdução

O setor de estética tem crescido exponencialmente nos últimos anos, impulsionado pelo aumento da preocupação com o bem-estar e a autoestima. Pequenos e médios empreendimentos enfrentam desafios na gestão eficiente de clientes, serviços e pagamentos. Este projeto visa desenvolver um sistema de gestão para microempresas de estética, utilizando Princípios de Programação Orientada a Objetos (POO) e modelagem de banco de dados para garantir uma solução robusta e escalável.

## 2. Análise do Negócio

### 2.1 Contexto do Mercado de Estética

O setor de estética movimenta bilhões de reais anualmente e tem apresentado crescimento significativo, principalmente pela busca constante por serviços especializados. Pequenos salões e clínicas de estética enfrentam desafios como:

- Organização de agenda de clientes.
- Controle financeiro e de pagamentos.
- Gerenciamento de serviços e pacotes promocionais.

A falta de uma gestão eficiente pode levar à perda de clientes e desorganização financeira.

### 2.2 Objetivo do Sistema

O sistema desenvolvido tem como objetivo:

- Automatizar o agendamento de consultas e serviços.
- Controlar pagamentos e planos de fidelidade.
- Armazenar dados de clientes e histórico de atendimentos.
- Garantir segurança e integridade dos dados.

## 3. Modelagem Orientada a Objetos

A POO é um paradigma essencial para modularização e reutilização de código. O sistema foi projetado utilizando os quatro pilares da POO:

### 3.1 Principais Classes

1. **Cliente**: Representa os clientes da clínica, armazenando informações como nome, telefone e histórico de atendimentos.
2. **Profissional**: Representa os profissionais do salão, com informações como especialidade e disponibilidade.
3. **Serviço**: Contém dados sobre os tipos de serviços oferecidos, preços e duração.
4. **Agendamento**: Gerencia o relacionamento entre Cliente, Profissional e Serviço.

### 3.2 Implementação das Classes em Java

```java
public class Cliente {
    private String nome;
    private String telefone;

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
}
```

```java
public class Servico {
    private String descricao;
    private double preco;

    public Servico(String descricao, double preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
}
```

## 4. Modelagem de Banco de Dados

A modelagem do banco de dados segue os conceitos de Modelo Entidade-Relacionamento (MER) e Diagrama de Entidade e Relacionamento (DER), garantindo estrutura eficiente para armazenamento de informações.

### 4.1 Principais Entidades e Relacionamentos

- **Cliente** (id, nome, telefone)
- **Profissional** (id, nome, especialidade)
- **Serviço** (id, descrição, preço)
- **Agendamento** (id, cliente_id, profissional_id, servico_id, data)

### 4.2 Script SQL de Criação do Banco

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

## 5. Conclusão

Este projeto propõe uma solução inovadora para a gestão de microempresas do setor de estética, utilizando conceitos de POO e modelagem de banco de dados. A implementação do sistema contribuirá para maior eficiência e segurança nos processos administrativos, melhorando a experiência dos clientes e otimizando o tempo dos profissionais.

Futuras melhorias incluem:

- Integração com sistemas de pagamento online.
- Desenvolvimento de um aplicativo móvel.
- Implementação de dashboards para análise de dados.

O sistema proposto se apresenta como uma solução escalável e adaptável às necessidades do mercado de estética.

