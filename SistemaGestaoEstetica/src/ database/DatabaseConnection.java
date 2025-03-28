import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utilitário de Conexão com Banco de Dados MySQL
 * Gerencia a conexão com o banco de dados para o Sistema de Gestão de Estética
 */
public class DatabaseConnection {
    // Configurações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_gestao_estetica";
    private static final String USUARIO = "root";
    private static final String SENHA = ""; // Substitua com sua senha real

    // Construtor privado para evitar instanciação
    private DatabaseConnection() {}

    /**
     * Estabelece uma conexão com o banco de dados MySQL
     * 
     * @return Connection objeto de conexão com o banco de dados
     * @throws SQLException se ocorrer um erro ao conectar
     */
    public static Connection obterConexao() throws SQLException {
        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Estabelecer e retornar a conexão
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            // Lançar SQLException se o driver não for encontrado
            throw new SQLException("Driver do MySQL não encontrado", e);
        }
    }

    /**
     * Fecha a conexão com o banco de dados
     * 
     * @param conexao Connection a ser fechada
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    /**
     * Método para testar a conexão com o banco de dados
     * 
     * @return boolean indicando se a conexão foi bem-sucedida
     */
    public static boolean testarConexao() {
        try (Connection conexao = obterConexao()) {
            return conexao != null && !conexao.isClosed();
        } catch (SQLException e) {
            System.err.println("Erro ao testar conexão: " + e.getMessage());
            return false;
        }
    }

    /**
     * Exemplo de uso no Main ou em outras classes
     * try (Connection conexao = DatabaseConnection.obterConexao()) {
     *     // Operações com o banco de dados
     * } catch (SQLException e) {
     *     // Tratamento de erro
     * }
     */
}
