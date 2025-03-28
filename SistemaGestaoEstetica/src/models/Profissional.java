package models;

import java.util.Date;

public class Profissional {
    private int id;
    private String nome;
    private String especialidade;
    private String telefone;
    private String email;
    private String cpf;
    private String endereco;
    private Date dataNascimento;

    // Construtor completo
    public Profissional(int id, String nome, String especialidade, String telefone,
                        String email, String cpf, String endereco, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    // Construtor simplificado para uso no DAO
    public Profissional(int id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    // Getters e Setters (unchanged)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Date getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
}