package dao;

import models.Profissional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO {
    private Connection connection;

    public ProfissionalDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirProfissional(Profissional profissional) throws SQLException {
        String sql = "INSERT INTO Profissional (nome, especialidade, telefone, email, cpf, endereco, data_nascimento) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, profissional.getNome());
        stmt.setString(2, profissional.getEspecialidade());
        stmt.setString(3, profissional.getTelefone());
        stmt.setString(4, profissional.getEmail());
        stmt.setString(5, profissional.getCpf());
        stmt.setString(6, profissional.getEndereco());
        stmt.setDate(7, new java.sql.Date(profissional.getDataNascimento().getTime()));
        stmt.executeUpdate();
        stmt.close();
    }

    public Profissional buscarProfissional(int id) throws SQLException {
        String sql = "SELECT * FROM Profissional WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Profissional profissional = null;
        if (rs.next()) {
            profissional = new Profissional(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("especialidade"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("endereco"),
                rs.getDate("data_nascimento")
            );
        }
        
        rs.close();
        stmt.close();
        return profissional;
    }

    public List<Profissional> listarProfissionais() throws SQLException {
        String sql = "SELECT * FROM Profissional";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<Profissional> profissionais = new ArrayList<>();
        while (rs.next()) {
            Profissional profissional = new Profissional(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("especialidade"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("endereco"),
                rs.getDate("data_nascimento")
            );
            profissionais.add(profissional);
        }
        
        rs.close();
        stmt.close();
        return profissionais;
    }

    public void atualizarProfissional(Profissional profissional) throws SQLException {
        String sql = "UPDATE Profissional SET nome = ?, especialidade = ?, telefone = ?, " +
                     "email = ?, cpf = ?, endereco = ?, data_nascimento = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, profissional.getNome());
        stmt.setString(2, profissional.getEspecialidade());
        stmt.setString(3, profissional.getTelefone());
        stmt.setString(4, profissional.getEmail());
        stmt.setString(5, profissional.getCpf());
        stmt.setString(6, profissional.getEndereco());
        stmt.setDate(7, new java.sql.Date(profissional.getDataNascimento().getTime()));
        stmt.setInt(8, profissional.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluirProfissional(int id) throws SQLException {
        String sql = "DELETE FROM Profissional WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Profissional> buscarProfissionaisPorEspecialidade(String especialidade) throws SQLException {
        String sql = "SELECT * FROM Profissional WHERE especialidade = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, especialidade);
        ResultSet rs = stmt.executeQuery();
        
        List<Profissional> profissionais = new ArrayList<>();
        while (rs.next()) {
            Profissional profissional = new Profissional(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("especialidade"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("endereco"),
                rs.getDate("data_nascimento")
            );
            profissionais.add(profissional);
        }
        
        rs.close();
        stmt.close();
        return profissionais;
    }
}
