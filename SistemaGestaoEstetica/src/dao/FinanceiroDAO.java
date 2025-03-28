package dao;

import models.Financeiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinanceiroDAO {
    private Connection connection;

    public FinanceiroDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirRegistroFinanceiro(Financeiro financeiro) throws SQLException {
        String sql = "INSERT INTO Financeiro (receita, despesa, saldo) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, financeiro.getReceita());
        stmt.setDouble(2, financeiro.getDespesa());
        stmt.setDouble(3, financeiro.calcularSaldo());
        stmt.executeUpdate();
        stmt.close();
    }

    public Financeiro buscarRegistroFinanceiro(int id) throws SQLException {
        String sql = "SELECT * FROM Financeiro WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Financeiro financeiro = null;
        if (rs.next()) {
            financeiro = new Financeiro(
                rs.getInt("id"),
                rs.getDouble("receita"),
                rs.getDouble("despesa")
            );
        }
        
        rs.close();
        stmt.close();
        return financeiro;
    }

    public List<Financeiro> listarRegistrosFinanceiros() throws SQLException {
        String sql = "SELECT * FROM Financeiro";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<Financeiro> registros = new ArrayList<>();
        while (rs.next()) {
            Financeiro financeiro = new Financeiro(
                rs.getInt("id"),
                rs.getDouble("receita"),
                rs.getDouble("despesa")
            );
            registros.add(financeiro);
        }
        
        rs.close();
        stmt.close();
        return registros;
    }

    public void atualizarRegistroFinanceiro(Financeiro financeiro) throws SQLException {
        String sql = "UPDATE Financeiro SET receita = ?, despesa = ?, saldo = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, financeiro.getReceita());
        stmt.setDouble(2, financeiro.getDespesa());
        stmt.setDouble(3, financeiro.calcularSaldo());
        stmt.setInt(4, financeiro.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluirRegistroFinanceiro(int id) throws SQLException {
        String sql = "DELETE FROM Financeiro WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public Financeiro obterResumoFinanceiro() throws SQLException {
        String sql = "SELECT SUM(receita) as total_receita, SUM(despesa) as total_despesa FROM Financeiro";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        Financeiro resumo = null;
        if (rs.next()) {
            double totalReceita = rs.getDouble("total_receita");
            double totalDespesa = rs.getDouble("total_despesa");
            resumo = new Financeiro(0, totalReceita, totalDespesa);
        }
        
        rs.close();
        stmt.close();
        return resumo;
    }
}
