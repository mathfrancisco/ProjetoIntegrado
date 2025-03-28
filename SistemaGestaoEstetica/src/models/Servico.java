package models;

/**
 * Representa um serviço oferecido no sistema de gestão de estética.
 * Inclui informações detalhadas sobre o serviço como nome, descrição, valor, e tipo.
 */
public class Servico {
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private int duracaoMinutos;
    private TipoServico tipo;

    /**
     * Construtor completo para criação de um serviço.
     *
     * @param id Identificador único do serviço
     * @param nome Nome do serviço
     * @param descricao Descrição detalhada do serviço
     * @param valor Preço do serviço
     * @param duracaoMinutos Duração do serviço em minutos
     * @param tipo Tipo de serviço (Facial ou Corporal)
     */
    public Servico(int id, String nome, String descricao, double valor,
                   int duracaoMinutos, TipoServico tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.duracaoMinutos = duracaoMinutos;
        this.tipo = tipo;
    }

    /**
     * Construtor simplificado para uso no DAO.
     * Utilizado quando apenas algumas informações são necessárias.
     *
     * @param id Identificador único do serviço
     * @param descricao Descrição do serviço
     * @param valor Preço do serviço
     */
    public Servico(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    /**
     * Enum que representa os tipos de serviço disponíveis.
     */
    public enum TipoServico {
        FACIAL,    // Serviços relacionados ao rosto
        CORPORAL   // Serviços relacionados ao corpo
    }

    // Getters e Setters com comentários explicativos

    /**
     * Obtém o identificador único do serviço.
     * @return Identificador do serviço
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único do serviço.
     * @param id Novo identificador do serviço
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do serviço.
     * @return Nome do serviço
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do serviço.
     * @param nome Novo nome do serviço
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do serviço.
     * @return Descrição do serviço
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do serviço.
     * @param descricao Nova descrição do serviço
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o valor do serviço.
     * @return Valor do serviço
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor do serviço.
     * @param valor Novo valor do serviço
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Obtém a duração do serviço em minutos.
     * @return Duração do serviço em minutos
     */
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    /**
     * Define a duração do serviço em minutos.
     * @param duracaoMinutos Nova duração do serviço
     */
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    /**
     * Obtém o tipo de serviço.
     * @return Tipo do serviço
     */
    public TipoServico getTipo() {
        return tipo;
    }

    /**
     * Define o tipo de serviço.
     * @param tipo Novo tipo de serviço
     */
    public void setTipo(TipoServico tipo) {
        this.tipo = tipo;
    }
}