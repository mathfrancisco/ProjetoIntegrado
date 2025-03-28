import java.util.Date;

public class Agendamento {
    private int id;
    private Cliente cliente;
    private Profissional profissional;
    private Servico servico;
    private Date data;
    private StatusAgendamento status;

    // Construtor completo
    public Agendamento(int id, Cliente cliente, Profissional profissional, 
                       Servico servico, Date data) {
        this.id = id;
        this.cliente = cliente;
        this.profissional = profissional;
        this.servico = servico;
        this.data = data;
    }

    // Enum para status de agendamento
    public enum StatusAgendamento {
        AGENDADO, 
        CONFIRMADO, 
        REALIZADO, 
        CANCELADO
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Profissional getProfissional() { return profissional; }
    public void setProfissional(Profissional profissional) { this.profissional = profissional; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public StatusAgendamento getStatus() { return status; }
    public void setStatus(StatusAgendamento status) { this.status = status; }
}
