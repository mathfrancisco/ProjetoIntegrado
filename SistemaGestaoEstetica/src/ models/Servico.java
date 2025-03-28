public class Servico {
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private int duracaoMinutos;
    private TipoServico tipo;

    // Construtor completo
    public Servico(int id, String nome, String descricao, double valor, 
                   int duracaoMinutos, TipoServico tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.duracaoMinutos = duracaoMinutos;
        this.tipo = tipo;
    }


    // Enum para tipos de serviço
    public enum TipoServico {
        FACIAL, 
        CORPORAL
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    public TipoServico getTipo() { return tipo; }
    public void setTipo(TipoServico tipo) { this.tipo = tipo; }
}
