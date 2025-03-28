import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceiroService {
    private FinanceiroDAO financeiroDAO;
    private AgendamentoDAO agendamentoDAO;
    private ProdutoDAO produtoDAO;

    public FinanceiroService(Connection connection) {
        this.financeiroDAO = new FinanceiroDAO(connection);
        this.agendamentoDAO = new AgendamentoDAO(connection);
        this.produtoDAO = new ProdutoDAO(connection);
    }

    /**
     * Calcula a receita total com base em agendamentos realizados
     * 
     * @return Valor total da receita
     * @throws SQLException em caso de erro de banco de dados
     */
    public double calcularReceitaAgendamentos() throws SQLException {
        List<Agendamento> agendamentos = agendamentoDAO.listarAgendamentos();
        
        return agendamentos.stream()
            .filter(a -> a.getStatus() == Agendamento.StatusAgendamento.REALIZADO)
            .mapToDouble(a -> a.getServico().getValor())
            .sum();
    }

    /**
     * Calcula a receita total de vendas de produtos
     * 
     * @return Valor total da receita de produtos
     * @throws SQLException em caso de erro de banco de dados
     */
    public double calcularReceitaProdutos() throws SQLException {
        List<Produto> produtos = produtoDAO.listarProdutos();
        
        return produtos.stream()
            .mapToDouble(p -> p.getQuantidade() * p.getPreco())
            .sum();
    }

    /**
     * Registra um novo lançamento financeiro
     * 
     * @param receita Valor da receita
     * @param despesa Valor da despesa
     * @return Registro financeiro criado
     * @throws SQLException em caso de erro de banco de dados
     */
    public Financeiro registrarLancamentoFinanceiro(double receita, double despesa) throws SQLException {
        if (receita < 0 || despesa < 0) {
            throw new IllegalArgumentException("Valores de receita e despesa devem ser não-negativos.");
        }

        Financeiro novoRegistro = new Financeiro(0, receita, despesa);
        financeiroDAO.inserirRegistroFinanceiro(novoRegistro);
        return novoRegistro;
    }

    /**
     * Obtém um resumo financeiro com receitas, despesas e saldo
     * 
     * @return Objeto Financeiro com resumo
     * @throws SQLException em caso de erro de banco de dados
     */
    public Financeiro obterResumoFinanceiro() throws SQLException {
        double receitaAgendamentos = calcularReceitaAgendamentos();
        double receitaProdutos = calcularReceitaProdutos();
        
        Financeiro resumoFinanceiro = financeiroDAO.obterResumoFinanceiro();
        resumoFinanceiro.setReceita(receitaAgendamentos + receitaProdutos);
        
        return resumoFinanceiro;
    }

    /**
     * Lista registros financeiros dentro de um período específico
     * 
     * @param dataInicio Data de início do período
     * @param dataFim Data de fim do período
     * @return Lista de registros financeiros no período
     * @throws SQLException em caso de erro de banco de dados
     */
    public List<Financeiro> listarRegistrosFinanceiros(java.util.Date dataInicio, java.util.Date dataFim) throws SQLException {
        List<Financeiro> todosRegistros = financeiroDAO.listarRegistrosFinanceiros();
        
        return todosRegistros.stream()
            // Adicione lógica de filtro por data quando adicionar timestamp nos registros financeiros
            .collect(Collectors.toList());
    }

    /**
     * Gera um relatório financeiro detalhado
     * 
     * @return String com relatório financeiro
     * @throws SQLException em caso de erro de banco de dados
     */
    public String gerarRelatorioFinanceiro() throws SQLException {
        Financeiro resumo = obterResumoFinanceiro();
        
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório Financeiro\n");
        relatorio.append("-------------------\n");
        relatorio.append(String.format("Receita Total: R$ %.2f\n", resumo.getReceita()));
        relatorio.append(String.format("Despesa Total: R$ %.2f\n", resumo.getDespesa()));
        relatorio.append(String.format("Saldo: R$ %.2f\n", resumo.calcularSaldo()));
        
        return relatorio.toString();
    }
}
