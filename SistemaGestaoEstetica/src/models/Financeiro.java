package models;

public class Financeiro {
    private int id;
    private double receita;
    private double despesa;

    // Construtor completo
    public Financeiro(int id, double receita, double despesa) {
        this.id = id;
        this.receita = receita;
        this.despesa = despesa;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getReceita() { return receita; }
    public void setReceita(double receita) { this.receita = receita; }

    public double getDespesa() { return despesa; }
    public void setDespesa(double despesa) { this.despesa = despesa; }

    // Método para calcular saldo
    public double calcularSaldo() {
        return receita - despesa;
    }
}
