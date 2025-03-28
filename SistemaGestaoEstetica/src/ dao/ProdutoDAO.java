import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produto (nome, quantidade, preco) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setDouble(3, produto.getPreco());
        stmt.executeUpdate();
        stmt.close();
    }

    public Produto buscarProduto(int id) throws SQLException {
        String sql = "SELECT * FROM Produto WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        Produto produto = null;
        if (rs.next()) {
            produto = new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("quantidade"),
                rs.getDouble("preco")
            );
        }
        
        rs.close();
        stmt.close();
        return produto;
    }

    public List<Produto> listarProdutos() throws SQLException {
        String sql = "SELECT * FROM Produto";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        List<Produto> produtos = new ArrayList<>();
        while (rs.next()) {
            Produto produto = new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("quantidade"),
                rs.getDouble("preco")
            );
            produtos.add(produto);
        }
        
        rs.close();
        stmt.close();
        return produtos;
    }

    public void atualizarProduto(Produto produto) throws SQLException {
        String sql = "UPDATE Produto SET nome = ?, quantidade = ?, preco = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setDouble(3, produto.getPreco());
        stmt.setInt(4, produto.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluirProduto(int id) throws SQLException {
        String sql = "DELETE FROM Produto WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public void atualizarEstoque(int id, int quantidadeAjuste) throws SQLException {
        String sql = "UPDATE Produto SET quantidade = quantidade + ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, quantidadeAjuste);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Produto> listarProdutosBaixoEstoque(int limiteMinimo) throws SQLException {
        String sql = "SELECT * FROM Produto WHERE quantidade <= ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, limiteMinimo);
        ResultSet rs = stmt.executeQuery();
        
        List<Produto> produtosBaixoEstoque = new ArrayList<>();
        while (rs.next()) {
            Produto produto = new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getInt("quantidade"),
                rs.getDouble("preco")
            );
            produtosBaixoEstoque.add(produto);
        }
        
        rs.close();
        stmt.close();
        return produtosBaixoEstoque;
    }
}
