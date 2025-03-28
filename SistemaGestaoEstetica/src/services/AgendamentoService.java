package services;

import dao.AgendamentoDAO;
import dao.ClienteDAO;
import dao.ProfissionalDAO;
import dao.ServicoDAO;
import models.Agendamento;
import models.Cliente;
import models.Profissional;
import models.Servico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AgendamentoService {
    private final AgendamentoDAO agendamentoDAO;
    private final ClienteDAO clienteDAO;
    private final ProfissionalDAO profissionalDAO;
    private final ServicoDAO servicoDAO;

    public AgendamentoService(Connection connection) {
        this.agendamentoDAO = new AgendamentoDAO(connection);
        this.clienteDAO = new ClienteDAO(connection);
        this.profissionalDAO = new ProfissionalDAO(connection);
        this.servicoDAO = new ServicoDAO(connection);
    }

    /**
     * Cria um novo agendamento com validações de negócio
     * 
     * @param clienteId ID do cliente
     * @param profissionalId ID do profissional
     * @param servicoId ID do serviço
     * @param data Data do agendamento
     * @return Agendamento criado
     * @throws SQLException em caso de erro de banco de dados
     * @throws IllegalArgumentException em caso de dados inválidos
     */
    public Agendamento criarAgendamento(int clienteId, int profissionalId, int servicoId, Date data) 
            throws SQLException, IllegalArgumentException {
        // Validar dados
        Cliente cliente = clienteDAO.buscarCliente(clienteId);
        Profissional profissional = profissionalDAO.buscarProfissional(profissionalId);
        Servico servico = servicoDAO.buscarServico(servicoId);

        if (cliente == null || profissional == null || servico == null) {
            throw new IllegalArgumentException("models.Cliente, models.Profissional ou Serviço inválido.");
        }

        // Verificar disponibilidade do profissional
        if (!verificarDisponibilidadeProfissional(profissionalId, data)) {
            throw new IllegalArgumentException("models.Profissional não está disponível na data e horário selecionados.");
        }

        // Criar agendamento
        Agendamento novoAgendamento = new Agendamento(0, cliente, profissional, servico, data);
        novoAgendamento.setStatus(Agendamento.StatusAgendamento.AGENDADO);

        agendamentoDAO.inserirAgendamento(novoAgendamento);
        return novoAgendamento;
    }

    /**
     * Verifica a disponibilidade do profissional em determinada data/hora
     * 
     * @param profissionalId ID do profissional
     * @param data Data a ser verificada
     * @return boolean indicando disponibilidade
     * @throws SQLException em caso de erro de banco de dados
     */
    public boolean verificarDisponibilidadeProfissional(int profissionalId, Date data) 
            throws SQLException {
        List<Agendamento> agendamentos = agendamentoDAO.listarAgendamentos();
        
        return agendamentos.stream()
            .filter(a -> a.getProfissional().getId() == profissionalId)
            .noneMatch(a -> datasConflitantes(a.getData(), data));
    }

    /**
     * Método auxiliar para verificar se duas datas estão em conflito
     * 
     * @param data1 Primeira data
     * @param data2 Segunda data
     * @return boolean indicando se há conflito
     */
    private boolean datasConflitantes(Date data1, Date data2) {
        // Lógica simplificada. Na implementação real, considere duração do serviço, janelas de tempo, etc.
        long diferencaMillis = Math.abs(data1.getTime() - data2.getTime());
        long umaHoraMillis = 60 * 60 * 1000; // 1 hora em milissegundos
        return diferencaMillis < umaHoraMillis;
    }

    /**
     * Atualiza o status de um agendamento
     * 
     * @param agendamentoId ID do agendamento
     * @param novoStatus Novo status do agendamento
     * @throws SQLException em caso de erro de banco de dados
     */
    public void atualizarStatusAgendamento(int agendamentoId, Agendamento.StatusAgendamento novoStatus) 
            throws SQLException {
        Agendamento agendamento = agendamentoDAO.buscarAgendamento(agendamentoId);
        
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não encontrado.");
        }
        
        agendamento.setStatus(novoStatus);
        agendamentoDAO.atualizarAgendamento(agendamento);
    }

    /**
     * Lista todos os agendamentos de um cliente
     * 
     * @param clienteId ID do cliente
     * @return Lista de agendamentos do cliente
     * @throws SQLException em caso de erro de banco de dados
     */
    public List<Agendamento> listarAgendamentosCliente(int clienteId) throws SQLException {
        List<Agendamento> todosAgendamentos = agendamentoDAO.listarAgendamentos();
        
        return todosAgendamentos.stream()
            .filter(a -> a.getCliente().getId() == clienteId)
            .toList();
    }
}
