# Sistema de Gestão para Microempresa de Estética

## Introdução

Este projeto visa desenvolver um sistema de gestão para microempresas de estética, utilizando os princípios da Programação Orientada a Objetos (POO) e modelagem de banco de dados. O objetivo é criar uma solução robusta e escalável que atenda às necessidades específicas do setor de estética, que tem crescido exponencialmente nos últimos anos.

## Objetivos do Projeto

- **Desenvolvimento de Classes**: Criar classes que representem as entidades do negócio, aplicando os princípios de POO: abstração, encapsulamento, herança e polimorfismo.
- **Modelagem de Banco de Dados**: Desenvolver um esquema de banco de dados relacional, utilizando diagramas Entidade-Relacionamento (DER) e scripts SQL para garantir integridade referencial.

## Requisitos do Projeto

### Requisitos de POO

1. **Abstração**: Identificar as características essenciais de cada entidade do negócio.
2. **Encapsulamento**: Proteger os dados e comportamentos internos das classes.
3. **Herança**: Estabelecer hierarquias entre classes que compartilham características comuns.
4. **Polimorfismo**: Permitir que diferentes tipos de serviços sejam tratados de maneira uniforme.

### Requisitos de Banco de Dados

1. **Modelagem de Entidades**: Definir entidades como Clientes, Profissionais, Serviços, Produtos, Fichas Técnicas e Agendamentos.
2. **Atributos e Relacionamentos**: Estabelecer atributos e relacionamentos entre as entidades.
3. **Chaves Primárias e Estrangeiras**: Garantir integridade referencial com chaves primárias e estrangeiras.
4. **Consultas SQL**: Implementar consultas para inserção, atualização, remoção e recuperação de dados.
5. **Diagrama Entidade-Relacionamento (DER)**: Representar visualmente a estrutura do banco de dados, incluindo entidades, atributos e relacionamentos.

## Funcionalidades

- **Gestão de Clientes**: Armazena informações detalhadas sobre os clientes.
- **Agendamento de Serviços**: Gerencia marcações de consultas e procedimentos.
- **Controle de Produtos e Estoque**: Monitora os produtos utilizados nos procedimentos.
- **Fichas Técnicas**: Permite o registro de avaliações e recomendações personalizadas.

## Modelagem Orientada a Objetos

### Principais Classes

1. **Cliente**: Representa os clientes da clínica.
2. **Profissional**: Representa os profissionais do salão.
3. **Serviço**: Contém dados sobre os tipos de serviços oferecidos.
4. **Agendamento**: Gerencia o relacionamento entre Cliente, Profissional e Serviço.

### Implementação das Classes em Java

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

    SERVICO_FACIAL {
        INT id_servico_facial PK AI
        INT id_servico FK
        ENUM('FACE_COMPLETA', 'OLHOS', 'LABIOS', 'BOCHECHAS', 'TESTA') regiao
        ENUM('BAIXA', 'MEDIA', 'ALTA') intensidade
    }

    SERVICO_CORPORAL {
        INT id_servico_corporal PK AI
        INT id_servico FK
        ENUM('PERNAS', 'BRACOS', 'COSTAS', 'ABDOMEN', 'GLUTEOS') area_corpo
        BOOLEAN necessita_avaliacao
    }

    PRODUTO {
        INT id_produto PK AI
        VARCHAR(100) nome
        VARCHAR(50) marca
        VARCHAR(20) lote
        DATE data_validade
        INT quantidade_estoque
        DECIMAL(10,2) valor_custo
    }

    FICHA_TECNICA {
        INT id_ficha PK AI
        INT id_cliente FK
        DATE data_avaliacao
        INT id_profissional FK
        TEXT observacoes
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

    PRODUTO_UTILIZADO {
        INT id_produto_utilizado PK AI
        INT id_servico FK
        INT id_produto FK
        DECIMAL(10,2) quantidade_padrao
    }

    CLIENTE ||--o{ FICHA_TECNICA : "1:N"
    CLIENTE ||--o{ AGENDAMENTO : "1:N"
    PROFISSIONAL ||--o{ AGENDAMENTO : "1:N"
    PROFISSIONAL ||--o{ FICHA_TECNICA : "1:N"
    SERVICO ||--o{ AGENDAMENTO : "1:N"
    SERVICO ||--o{ PRODUTO_UTILIZADO : "1:N"
    SERVICO ||--o|| SERVICO_FACIAL : "1:1"
    SERVICO ||--o|| SERVICO_CORPORAL : "1:1"
    PRODUTO ||--o{ PRODUTO_UTILIZADO : "1:N"
    SERVICO_FACIAL }o--|| SERVICO : "1:1"
    SERVICO_CORPORAL }o--|| SERVICO : "1:1"

```

### Script SQL de Criação do Banco

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

- **Gestão Eficiente**: O sistema automatiza o agendamento e controle de estoque.
- **Melhoria no Atendimento**: Permite um atendimento personalizado com fichas técnicas detalhadas.
- **Escalabilidade**: A modelagem POO e o banco de dados relacional garantem que o sistema cresça junto com a empresa.

## Futuras Melhorias

- **Integração com Pagamentos Online**: Implementar sistemas de pagamento eletrônico.
- **Desenvolvimento de Aplicativo Móvel**: Oferecer uma interface móvel para clientes e profissionais.
- **Dashboards para Análise de Dados**: Incluir ferramentas de análise para melhorar a tomada de decisões.


Citations:
[1] https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/31488785/c14fb259-16e5-405b-bf2d-51ae31cc7caf/paste.txt
