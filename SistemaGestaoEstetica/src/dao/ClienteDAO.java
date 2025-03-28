package dao;

import models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, telefone) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getTelefone());
        stmt.executeUpdate();
        stmt.close();
    }

    public Cliente buscarCliente(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"));
        }

        rs.close();
        stmt.close();
        return cliente;
    }
}

