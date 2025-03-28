package dao;

import models.Servico;
import models.Servico.TipoServico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    private Connection connection;

    public ServicoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirServico(Servico servico) throws SQLException {
        String sql = "INSERT INTO Servico (nome, descricao, valor, duracao_minutos, tipo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, servico.getNome());
        stmt.setString(2, servico.getDescricao());
        stmt.setDouble(3, servico.getValor());
        stmt.setInt(4, servico.getDuracaoMinutos());
        stmt.setString(5, servico.getTipo().name());
        stmt.executeUpdate();
        stmt.close();
    }

    public Servico buscarServico(int id) throws SQLException {
        String sql = "SELECT * FROM Servico WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Servico servico = null;
        if (rs.next()) {
            servico = new Servico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getInt("duracao_minutos"),
                    TipoServico.valueOf(rs.getString("tipo"))
            );
        }

        rs.close();
        stmt.close();
        return servico;
    }

    public List<Servico> listarServicos() throws SQLException {
        String sql = "SELECT * FROM Servico";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Servico> servicos = new ArrayList<>();
        while (rs.next()) {
            Servico servico = new Servico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getInt("duracao_minutos"),
                    TipoServico.valueOf(rs.getString("tipo"))
            );
            servicos.add(servico);
        }

        rs.close();
        stmt.close();
        return servicos;
    }

    public void atualizarServico(Servico servico) throws SQLException {
        String sql = "UPDATE Servico SET nome = ?, descricao = ?, valor = ?, duracao_minutos = ?, tipo = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, servico.getNome());
        stmt.setString(2, servico.getDescricao());
        stmt.setDouble(3, servico.getValor());
        stmt.setInt(4, servico.getDuracaoMinutos());
        stmt.setString(5, servico.getTipo().name());
        stmt.setInt(6, servico.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluirServico(int id) throws SQLException {
        String sql = "DELETE FROM Servico WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Servico> buscarServicosPorTipo(TipoServico tipo) throws SQLException {
        String sql = "SELECT * FROM Servico WHERE tipo = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, tipo.name());
        ResultSet rs = stmt.executeQuery();

        List<Servico> servicos = new ArrayList<>();
        while (rs.next()) {
            Servico servico = new Servico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("valor"),
                    rs.getInt("duracao_minutos"),
                    TipoServico.valueOf(rs.getString("tipo"))
            );
            servicos.add(servico);
        }

        rs.close();
        stmt.close();
        return servicos;
    }
}