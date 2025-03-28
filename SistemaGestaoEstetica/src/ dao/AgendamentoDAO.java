import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {
    private Connection connection;

    public AgendamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirAgendamento(Agendamento agendamento) throws SQLException {
        String sql = "INSERT INTO Agendamento (cliente_id, profissional_id, servico_id, data) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, agendamento.getCliente().getId());
        stmt.setInt(2, agendamento.getProfissional().getId());
        stmt.setInt(3, agendamento.getServico().getId());
        stmt.setTimestamp(4, new java.sql.Timestamp(agendamento.getData().getTime()));
        stmt.executeUpdate();
        stmt.close();
    }

    public Agendamento buscarAgendamento(int id) throws SQLException {
        String sql = "SELECT a.*, c.nome as cliente_nome, c.telefone as cliente_telefone, " +
                     "p.nome as profissional_nome, p.especialidade as profissional_especialidade, " +
                     "s.descricao as servico_descricao, s.preco as servico_preco " +
                     "FROM Agendamento a " +
                     "JOIN Cliente c ON a.cliente_id = c.id " +
                     "JOIN Profissional p ON a.profissional_id = p.id " +
                     "JOIN Servico s ON a.servico_id = s.id " +
                     "WHERE a.id = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Agendamento agendamento = null;
        if (rs.next()) {
            Cliente cliente = new Cliente(rs.getInt("cliente_id"), 
                                          rs.getString("cliente_nome"), 
                                          rs.getString("cliente_telefone"));
            
            Profissional profissional = new Profissional(rs.getInt("profissional_id"),
                                                         rs.getString("profissional_nome"),
                                                         rs.getString("profissional_especialidade"));
            
            Servico servico = new Servico(rs.getInt("servico_id"),
                                          rs.getString("servico_descricao"),
                                          rs.getDouble("servico_preco"));
            
            agendamento = new Agendamento(rs.getInt("id"), 
                                          cliente, 
                                          profissional, 
                                          servico, 
                                          rs.getTimestamp("data"));
        }
        
        rs.close();
        stmt.close();
        return agendamento;
    }

    public List<Agendamento> listarAgendamentos() throws SQLException {
        String sql = "SELECT a.*, c.nome as cliente_nome, c.telefone as cliente_telefone, " +
                     "p.nome as profissional_nome, p.especialidade as profissional_especialidade, " +
                     "s.descricao as servico_descricao, s.preco as servico_preco " +
                     "FROM Agendamento a " +
                     "JOIN Cliente c ON a.cliente_id = c.id " +
                     "JOIN Profissional p ON a.profissional_id = p.id " +
                     "JOIN Servico s ON a.servico_id = s.id";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<Agendamento> agendamentos = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente = new Cliente(rs.getInt("cliente_id"), 
                                          rs.getString("cliente_nome"), 
                                          rs.getString("cliente_telefone"));
            
            Profissional profissional = new Profissional(rs.getInt("profissional_id"),
                                                         rs.getString("profissional_nome"),
                                                         rs.getString("profissional_especialidade"));
            
            Servico servico = new Servico(rs.getInt("servico_id"),
                                          rs.getString("servico_descricao"),
                                          rs.getDouble("servico_preco"));
            
            Agendamento agendamento = new Agendamento(rs.getInt("id"), 
                                                      cliente, 
                                                      profissional, 
                                                      servico, 
                                                      rs.getTimestamp("data"));
            
            agendamentos.add(agendamento);
        }
        
        rs.close();
        stmt.close();
        return agendamentos;
    }

    public void atualizarAgendamento(Agendamento agendamento) throws SQLException {
        String sql = "UPDATE Agendamento SET cliente_id = ?, profissional_id = ?, servico_id = ?, data = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, agendamento.getCliente().getId());
        stmt.setInt(2, agendamento.getProfissional().getId());
        stmt.setInt(3, agendamento.getServico().getId());
        stmt.setTimestamp(4, new java.sql.Timestamp(agendamento.getData().getTime()));
        stmt.setInt(5, agendamento.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluirAgendamento(int id) throws SQLException {
        String sql = "DELETE FROM Agendamento WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
