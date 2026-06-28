import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Camada de serviço: concentra as COLEÇÕES (R10) e as regras de negócio com exceções (R9).
// É aqui que ficam os "métodos de serviço" que declaram throws; o Main captura.
public class ClinicaServico {

    // R10 LIST: lista unificada de todas as pessoas (para o relatório de cadastros e ligação dinâmica).
    // ArrayList<Pessoa>: ordem de inserção importa e percorremos para o relatório geral.
    private List<Pessoa> todasPessoas = new ArrayList<>();

    // R10 MAP: HashMap CPF -> Paciente (busca por chave; mais eficiente que percorrer lista).
    private Map<String, Paciente> pacientesPorCpf = new HashMap<>();
    // R10 MAP: HashMap nome -> Profissional (busca por nome, conforme especificação).
    private Map<String, Profissional> profissionaisPorNome = new HashMap<>();

    // R10 SET: HashSet apenas verifica existência (contains) de CPF; não precisa de ordem.
    private Set<String> cpfsCadastrados = new HashSet<>();

    // R10 LIST: consultas, atendimentos e pagamentos em listas editáveis.
    private List<Consulta> consultas = new ArrayList<>();
    private List<Atendimento> atendimentos = new ArrayList<>();
    private List<Pagamento> pagamentos = new ArrayList<>();

    // convênios da clínica (definidos pelo professor)
    private List<Convenio> convenios = new ArrayList<>();

    public ClinicaServico() {
        carregarConvenios();
    }

    // Os 3 convênios nomeados no enunciado (SaudePlus 40%, VidaMais 30%, BemEstar 50%).
    private void carregarConvenios() {
        List<String> espSaude = new ArrayList<>();
        espSaude.add("clinica geral"); espSaude.add("fisioterapia"); espSaude.add("psicologia");
        convenios.add(new Convenio("SaudePlus", 0.40, espSaude));

        List<String> espVida = new ArrayList<>();
        espVida.add("clinica geral"); espVida.add("nutricao");
        convenios.add(new Convenio("VidaMais", 0.30, espVida));

        List<String> espBem = new ArrayList<>();
        espBem.add("clinica geral"); espBem.add("fisioterapia");
        espBem.add("psicologia"); espBem.add("nutricao");
        convenios.add(new Convenio("BemEstar", 0.50, espBem));
    }

    // ===================== CADASTROS =====================

    public void cadastrarPaciente(Paciente p) throws OperacaoInvalidaException {
        // R10 (Set): add() retorna false se o CPF já existe -> impede duplicidade.
        if (!cpfsCadastrados.add(p.getCpf())) {
            throw new OperacaoInvalidaException("CPF " + p.getCpf() + " ja cadastrado.");
        }
        pacientesPorCpf.put(p.getCpf(), p); // R10 (Map): put
        todasPessoas.add(p);                // R10 (List): add
    }

    public void cadastrarProfissional(Profissional prof) throws OperacaoInvalidaException {
        if (!cpfsCadastrados.add(prof.getCpf())) {
            throw new OperacaoInvalidaException("CPF " + prof.getCpf() + " ja cadastrado.");
        }
        profissionaisPorNome.put(prof.getNome().toLowerCase(), prof);
        todasPessoas.add(prof);
    }

    // ===================== BUSCAS =====================

    public Paciente buscarPaciente(String cpf) throws PacienteNaoEncontradoException {
        // R10 (Map): containsKey/get
        if (!pacientesPorCpf.containsKey(cpf)) {
            throw new PacienteNaoEncontradoException("Paciente com CPF " + cpf + " nao encontrado.");
        }
        return pacientesPorCpf.get(cpf);
    }

    public Profissional buscarProfissional(String nome) throws ProfissionalNaoEncontradoException {
        String chave = nome.toLowerCase();
        if (!profissionaisPorNome.containsKey(chave)) {
            throw new ProfissionalNaoEncontradoException("Profissional '" + nome + "' nao encontrado.");
        }
        return profissionaisPorNome.get(chave);
    }

    public Convenio buscarConvenio(String nome) throws OperacaoInvalidaException {
        for (Convenio c : convenios) {
            if (c.getNome().equalsIgnoreCase(nome)) return c;
        }
        throw new OperacaoInvalidaException("Convenio '" + nome + "' nao cadastrado.");
    }

    // R9: cenário 13 — busca por CPF + data + horário sem resultado.
    public Consulta buscarConsulta(String cpf, String data, String horario) throws ConsultaNaoEncontradaException {
        for (Consulta c : consultas) { // R10 (List): for-each
            if (c.getCpfPaciente().equals(cpf) && c.getData().equals(data) && c.getHorario().equals(horario)) {
                return c;
            }
        }
        throw new ConsultaNaoEncontradaException("Consulta nao encontrada para CPF " + cpf
            + " em " + data + " as " + horario + ".");
    }

    private boolean temConflito(String nomeProfissional, String data, String horario) {
        for (Consulta c : consultas) {
            if (c.getNomeProfissional().equalsIgnoreCase(nomeProfissional)
                    && c.getData().equals(data) && c.getHorario().equals(horario)
                    && c.getStatus().equals("agendada")) {
                return true;
            }
        }
        return false;
    }

    // ===================== OPERAÇÕES DE NEGÓCIO =====================

    // R9: throws ESPECÍFICOS. Cobre cenários 2,3,4,5,6.
    public Consulta agendarConsulta(String cpf, String nomeProfissional,
                                    String data, String horario, String diaSemana)
            throws PacienteNaoEncontradoException, ProfissionalNaoEncontradoException,
                   PacienteInativoException, HorarioIndisponivelException {

        Paciente paciente = buscarPaciente(cpf);                  // cenário 3
        if (!paciente.isAtivo()) {                                // cenário 2
            throw new PacienteInativoException("Paciente " + paciente.getNome()
                + " esta INATIVO; nao e permitido agendar.");
        }
        Profissional prof = buscarProfissional(nomeProfissional); // cenário 4
        if (!prof.atendeNoDia(diaSemana)) {                       // cenário 6
            throw new HorarioIndisponivelException("Profissional " + nomeProfissional
                + " nao atende em '" + diaSemana + "'.");
        }
        if (temConflito(nomeProfissional, data, horario)) {       // cenário 5
            throw new HorarioIndisponivelException("Horario " + horario + " em " + data
                + " ja esta ocupado para " + nomeProfissional + ".");
        }
        Consulta nova = new Consulta(cpf, nomeProfissional, data, horario);
        consultas.add(nova);
        return nova;
    }

    // Agendamento por especialidade (funcionalidade da base): acha um profissional disponível.
    public Consulta agendarPorEspecialidade(String cpf, String especialidade,
                                            String data, String horario, String diaSemana)
            throws PacienteNaoEncontradoException, ProfissionalNaoEncontradoException,
                   PacienteInativoException, HorarioIndisponivelException {

        Paciente paciente = buscarPaciente(cpf);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException("Paciente " + paciente.getNome() + " esta INATIVO.");
        }
        for (Profissional prof : profissionaisPorNome.values()) { // R10 (Map): values()
            if (prof.getEspecialidade().equals(especialidade)
                    && prof.atendeNoDia(diaSemana)
                    && !temConflito(prof.getNome(), data, horario)) {
                Consulta nova = new Consulta(cpf, prof.getNome(), data, horario);
                consultas.add(nova);
                return nova;
            }
        }
        throw new HorarioIndisponivelException("Nenhum profissional de '" + especialidade
            + "' disponivel em " + data + " (" + diaSemana + ") as " + horario + ".");
    }

    public Consulta cancelarConsulta(String cpf, String data, String horario)
            throws ConsultaNaoEncontradaException, OperacaoInvalidaException {
        Consulta c = buscarConsulta(cpf, data, horario); // cenário 13
        Agendavel agendavel = c;                         // R7: uso polimórfico via interface
        agendavel.cancelar();                            // cenários 7 e 8
        return c;
    }

    public Consulta remarcarConsulta(String cpf, String data, String horario,
                                     String novaData, String novoHorario, String novoDiaSemana)
            throws ConsultaNaoEncontradaException, OperacaoInvalidaException,
                   ProfissionalNaoEncontradoException, HorarioIndisponivelException {
        Consulta c = buscarConsulta(cpf, data, horario);
        Profissional prof = buscarProfissional(c.getNomeProfissional());
        if (!prof.atendeNoDia(novoDiaSemana)) {
            throw new HorarioIndisponivelException("Profissional nao atende em '" + novoDiaSemana + "'.");
        }
        if (temConflito(c.getNomeProfissional(), novaData, novoHorario)) {
            throw new HorarioIndisponivelException("Novo horario ja ocupado.");
        }
        Agendavel agendavel = c;                 // R7: via interface
        agendavel.remarcar();                    // marca original como "remarcada" (OperacaoInvalida se nao agendada)
        Consulta nova = new Consulta(cpf, c.getNomeProfissional(), novaData, novoHorario, c.getTipo());
        consultas.add(nova);                     // nova consulta agendada (preserva o historico)
        return nova;
    }

    // Jornada 7/19: sugere um horario livre no mesmo dia (vazio se o profissional nao atende no dia).
    public String sugerirHorario(String nomeProfissional, String data, String diaSemana)
            throws ProfissionalNaoEncontradoException {
        Profissional prof = buscarProfissional(nomeProfissional);
        if (!prof.atendeNoDia(diaSemana)) return "";
        for (int h = 8; h <= 18; h++) {
            String teste = (h < 10 ? "0" + h : "" + h) + ":00";
            if (!temConflito(nomeProfissional, data, teste)) return teste;
        }
        return "";
    }

    // Busca consulta pelo indice na lista (usada por atendimento e pagamento).
    public Consulta buscarConsultaPorIndice(int indice) throws ConsultaNaoEncontradaException {
        if (indice < 0 || indice >= consultas.size()) {
            throw new ConsultaNaoEncontradaException("Indice de consulta invalido: " + indice);
        }
        return consultas.get(indice);
    }

    public Atendimento registrarAtendimento(int indiceConsulta, String observacoes, String diagnostico,
                                            String[] procedimentos)
            throws ConsultaNaoEncontradaException, ProfissionalNaoEncontradoException, OperacaoInvalidaException {
        if (indiceConsulta < 0 || indiceConsulta >= consultas.size()) {
            throw new ConsultaNaoEncontradaException("Indice de consulta invalido: " + indiceConsulta);
        }
        Consulta c = consultas.get(indiceConsulta); // R10 (List): get(index)
        c.realizar();                               // cenário 9

        Atendimento at;
        if (procedimentos != null && procedimentos.length > 0) {
            at = new Atendimento(indiceConsulta, c.getData(), observacoes, diagnostico, procedimentos);
        } else if (diagnostico != null && !diagnostico.isEmpty()) {
            at = new Atendimento(indiceConsulta, c.getData(), observacoes, diagnostico);
        } else {
            at = new Atendimento(indiceConsulta, c.getData(), observacoes);
        }
        // R5 LIGAÇÃO DINÂMICA: chama registrarEspecifico() do tipo REAL do profissional.
        Profissional prof = buscarProfissional(c.getNomeProfissional());
        prof.registrarEspecifico(at);

        atendimentos.add(at);
        return at;
    }

    public void adicionarPagamento(Pagamento p) { pagamentos.add(p); }

    // ===================== ACESSO ÀS COLEÇÕES =====================
    public List<Pessoa> getTodasPessoas() { return todasPessoas; }
    public List<Consulta> getConsultas() { return consultas; }
    public List<Atendimento> getAtendimentos() { return atendimentos; }
    public List<Pagamento> getPagamentos() { return pagamentos; }
    public List<Convenio> getConvenios() { return convenios; }
    public Map<String, Paciente> getPacientesPorCpf() { return pacientesPorCpf; }
    public Map<String, Profissional> getProfissionaisPorNome() { return profissionaisPorNome; }
}
