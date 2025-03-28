import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Scanner scanner;
    
    private static AgendamentoService agendamentoService;
    private static FinanceiroService financeiroService;
    private static ClienteDAO clienteDAO;
    private static ProfissionalDAO profissionalDAO;
    private static ServicoDAO servicoDAO;

    public static void main(String[] args) {
        try {
            // Estabelecer conexão com o banco de dados
            connection = DatabaseConnection.obterConexao();
            scanner = new Scanner(System.in);

            // Inicializar serviços e DAOs
            agendamentoService = new AgendamentoService(connection);
            financeiroService = new FinanceiroService(connection);
            clienteDAO = new ClienteDAO(connection);
            profissionalDAO = new ProfissionalDAO(connection);
            servicoDAO = new ServicoDAO(connection);

            // Menu principal
            exibirMenuPrincipal();

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            // Fechar recursos
            fecharRecursos();
        }
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n--- Sistema de Gestão para Microempresa de Estética ---");
            System.out.println("1. Gerenciar Agendamentos");
            System.out.println("2. Gerenciar Financeiro");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Cadastrar Profissional");
            System.out.println("5. Cadastrar Serviço");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    menuAgendamentos();
                    break;
                case 2:
                    menuFinanceiro();
                    break;
                case 3:
                    cadastrarCliente();
                    break;
                case 4:
                    cadastrarProfissional();
                    break;
                case 5:
                    cadastrarServico();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuAgendamentos() {
        while (true) {
            System.out.println("\n--- Gerenciar Agendamentos ---");
            System.out.println("1. Criar Agendamento");
            System.out.println("2. Listar Agendamentos do Cliente");
            System.out.println("3. Atualizar Status do Agendamento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            try {
                switch (opcao) {
                    case 1:
                        criarAgendamento();
                        break;
                    case 2:
                        listarAgendamentosCliente();
                        break;
                    case 3:
                        atualizarStatusAgendamento();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao processar agendamento: " + e.getMessage());
            }
        }
    }

    private static void menuFinanceiro() {
        while (true) {
            System.out.println("\n--- Gerenciar Financeiro ---");
            System.out.println("1. Registrar Lançamento Financeiro");
            System.out.println("2. Gerar Relatório Financeiro");
            System.out.println("3. Obter Resumo Financeiro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            try {
                switch (opcao) {
                    case 1:
                        registrarLancamentoFinanceiro();
                        break;
                    case 2:
                        gerarRelatorioFinanceiro();
                        break;
                    case 3:
                        obterResumoFinanceiro();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao processar operação financeira: " + e.getMessage());
            }
        }
    }

    private static void criarAgendamento() throws SQLException {
        System.out.print("ID do Cliente: ");
        int clienteId = scanner.nextInt();
        System.out.print("ID do Profissional: ");
        int profissionalId = scanner.nextInt();
        System.out.print("ID do Serviço: ");
        int servicoId = scanner.nextInt();
        
        Agendamento agendamento = agendamentoService.criarAgendamento(
            clienteId, profissionalId, servicoId, new Date()
        );
        System.out.println("Agendamento criado com sucesso. ID: " + agendamento.getId());
    }

    private static void listarAgendamentosCliente() throws SQLException {
        System.out.print("ID do Cliente: ");
        int clienteId = scanner.nextInt();
        
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentosCliente(clienteId);
        agendamentos.forEach(a -> System.out.println(
            "ID: " + a.getId() + 
            ", Serviço: " + a.getServico().getNome() + 
            ", Data: " + a.getData()
        ));
    }

    private static void atualizarStatusAgendamento() throws SQLException {
        System.out.print("ID do Agendamento: ");
        int agendamentoId = scanner.nextInt();
        
        System.out.println("Selecione o novo status:");
        for (Agendamento.StatusAgendamento status : Agendamento.StatusAgendamento.values()) {
            System.out.println(status.ordinal() + 1 + ". " + status);
        }
        
        int statusIndex = scanner.nextInt() - 1;
        Agendamento.StatusAgendamento novoStatus = Agendamento.StatusAgendamento.values()[statusIndex];
        
        agendamentoService.atualizarStatusAgendamento(agendamentoId, novoStatus);
        System.out.println("Status atualizado com sucesso.");
    }

    private static void registrarLancamentoFinanceiro() throws SQLException {
        System.out.print("Valor da Receita: ");
        double receita = scanner.nextDouble();
        System.out.print("Valor da Despesa: ");
        double despesa = scanner.nextDouble();
        
        Financeiro lancamento = financeiroService.registrarLancamentoFinanceiro(receita, despesa);
        System.out.println("Lançamento financeiro registrado. Saldo: " + lancamento.calcularSaldo());
    }

    private static void gerarRelatorioFinanceiro() throws SQLException {
        String relatorio = financeiroService.gerarRelatorioFinanceiro();
        System.out.println(relatorio);
    }

    private static void obterResumoFinanceiro() throws SQLException {
        Financeiro resumo = financeiroService.obterResumoFinanceiro();
        System.out.println("Receita Total: R$ " + resumo.getReceita());
        System.out.println("Despesa Total: R$ " + resumo.getDespesa());
        System.out.println("Saldo: R$ " + resumo.calcularSaldo());
    }

    private static void cadastrarCliente() {
        try {
            System.out.print("Nome do Cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Telefone do Cliente: ");
            String telefone = scanner.nextLine();
            
            Cliente cliente = new Cliente(0, nome, telefone);
            clienteDAO.inserirCliente(cliente);
            System.out.println("Cliente cadastrado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void cadastrarProfissional() {
        try {
            System.out.print("Nome do Profissional: ");
            String nome = scanner.nextLine();
            System.out.print("Especialidade: ");
            String especialidade = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            
            Profissional profissional = new Profissional(0, nome, especialidade, telefone, 
                                                         email, cpf, endereco, new Date());
            profissionalDAO.inserirProfissional(profissional);
            System.out.println("Profissional cadastrado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar profissional: " + e.getMessage());
        }
    }

    private static void cadastrarServico() {
        try {
            System.out.print("Nome do Serviço: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            System.out.print("Duração (minutos): ");
            int duracao = scanner.nextInt();
            
            System.out.println("Selecione o Tipo de Serviço:");
            for (Servico.TipoServico tipo : Servico.TipoServico.values()) {
                System.out.println(tipo.ordinal() + 1 + ". " + tipo);
            }
            int tipoIndex = scanner.nextInt() - 1;
            Servico.TipoServico tipoServico = Servico.TipoServico.values()[tipoIndex];
            
            Servico servico = new Servico(0, nome, descricao, valor, duracao, tipoServico);
            servicoDAO.inserirServico(servico);
            System.out.println("Serviço cadastrado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar serviço: " + e.getMessage());
        }
    }

    private static void fecharRecursos() {
        try {
            if (scanner != null) {
                scanner.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}
