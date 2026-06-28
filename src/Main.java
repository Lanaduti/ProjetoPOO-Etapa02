

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    
    private static final Scanner sc = new Scanner(System.in);
    private static final ClinicaServico service = new ClinicaServico();

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== CLINICA VIDAPLENA ===");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Atendimentos");
            System.out.println("5 - Pagamentos");
            System.out.println("6 - Relatorios");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Escolha: ");
            switch (opcao) {
                case 1: menuPacientes(); break;
                case 2: menuProfissionais(); break;
                case 3: menuConsultas(); break;
                case 4: menuAtendimentos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuRelatorios(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
        System.out.println("Sistema encerrado.");
    }

    // ============================== PACIENTES ==============================
    private static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");
            switch (op) {
                case 1: cadastrarPaciente(); break;
                case 2: complementarPaciente(); break;
                case 3: buscarPaciente(); break;
                case 4: listarPacientes(); break;
                case 5: desativarPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int tipo = lerInteiro("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");
        try {
            Paciente p;
            if (tipo == 1) {
                p = new Paciente(nome, cpf);
            } else if (tipo == 2) {
                int idade = lerInteiro("Idade: ");
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                p = new Paciente(nome, cpf, idade, tel);
            } else {
                int idade = lerInteiro("Idade: ");
                System.out.print("Telefone: ");
                String tel = sc.nextLine();
                Convenio conv = lerConvenio();
                p = new Paciente(nome, cpf, idade, tel, conv);
            }
            service.cadastrarPaciente(p);
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Dado invalido: " + e.getMessage());
        }
    }

    private static void complementarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            Paciente p = service.buscarPaciente(cpf);
            int tem = lerInteiro("Vai informar convenio? (1-Nao / 2-Sim): ");
            int idade = lerInteiro("Idade: ");
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            if (tem == 1) {
                p.complementar(idade, tel);
            } else {
                p.complementar(idade, tel, lerConvenio());
            }
            System.out.println("Cadastro atualizado!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Dado invalido: " + e.getMessage());
        }
    }

    private static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            System.out.println(service.buscarPaciente(cpf).exibirResumo());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        Map<String, Paciente> mapa = service.getPacientesPorCpf();
        if (mapa.isEmpty()) { System.out.println("Nenhum paciente cadastrado."); return; }
        for (Paciente p : mapa.values()) {           // R10 (Map): values()
            System.out.println(p.exibirResumo());
        }
    }

    private static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        try {
            service.buscarPaciente(cpf).desativar();
            System.out.println("Paciente desativado.");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ============================== PROFISSIONAIS ==============================
    private static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar cadastro");
            System.out.println("3 - Listar todos");
            System.out.println("4 - Filtrar por especialidade");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");
            switch (op) {
                case 1: cadastrarProfissional(); break;
                case 2: atualizarProfissional(); break;
                case 3: listarProfissionais(); break;
                case 4: filtrarProfissionais(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void cadastrarProfissional() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
        String esp = sc.nextLine();
        if (!Profissional.especialidadeValida(esp)) {
            System.out.println("Especialidade invalida!");
            return;
        }
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        System.out.print("Data de nascimento (DD/MM/AAAA): ");
        String nasc = sc.nextLine();
        System.out.print("Registro profissional: ");
        String reg = sc.nextLine();
        double valor = lerDouble("Valor da consulta: ");
        try {
            Profissional prof;
            // a especialidade determina a especialização concreta (R3)
            if (esp.equals("fisioterapia")) {
                int sessoes = lerInteiro("Total de sessoes previstas: ");
                prof = new Fisioterapeuta(nome, cpf, tel, nasc, reg, valor, sessoes);
            } else if (esp.equals("psicologia")) {
                System.out.print("Abordagem (TCC/psicanalise/humanista): ");
                prof = new Psicologo(nome, cpf, tel, nasc, reg, valor, sc.nextLine());
            } else if (esp.equals("nutricao")) {
                System.out.print("Plano alimentar: ");
                prof = new Nutricionista(nome, cpf, tel, nasc, reg, valor, sc.nextLine());
            } else {
                System.out.print("Encaminhamento (ou ENTER): ");
                prof = new ClinicoGeral(nome, cpf, tel, nasc, reg, valor, sc.nextLine());
            }
            int qtd = lerInteiro("Quantos dias atende? ");
            for (int i = 0; i < qtd; i++) {
                System.out.print("Dia " + (i + 1) + " (segunda/terca/...): ");
                String dia = sc.nextLine();
                System.out.print("Turno (manha/tarde): ");
                String turno = sc.nextLine();
                prof.adicionarHorario(dia, turno); // R8 agregação
            }
            service.cadastrarProfissional(prof);
            System.out.println("Profissional cadastrado!");
        } catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Dado invalido: " + e.getMessage());
        }
    }

    private static void atualizarProfissional() {
        System.out.print("Nome do profissional: ");
        String nome = sc.nextLine();
        try {
            Profissional prof = service.buscarProfissional(nome);
            System.out.print("Novo registro: ");
            prof.setRegistroProfissional(sc.nextLine());
            prof.setValorConsulta(lerDouble("Novo valor da consulta: "));
            int add = lerInteiro("Adicionar dias? (quantidade, 0 para nenhum): ");
            for (int i = 0; i < add; i++) {
                System.out.print("Dia: ");
                String dia = sc.nextLine();
                System.out.print("Turno (manha/tarde): ");
                String turno = sc.nextLine();
                prof.adicionarHorario(dia, turno);
            }
            System.out.println("Profissional atualizado!");
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Dado invalido: " + e.getMessage());
        }
    }

    private static void listarProfissionais() {
        Map<String, Profissional> mapa = service.getProfissionaisPorNome();
        if (mapa.isEmpty()) { System.out.println("Nenhum profissional cadastrado."); return; }
        for (Map.Entry<String, Profissional> e : mapa.entrySet()) { // R10 (Map): entrySet()
            System.out.println(e.getValue().exibirResumo());
        }
    }

    private static void filtrarProfissionais() {
        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        boolean achou = false;
        for (Profissional prof : service.getProfissionaisPorNome().values()) {
            if (prof.getEspecialidade().equals(esp)) {
                System.out.println(prof.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum profissional com essa especialidade.");
    }

    // ============================== CONSULTAS ==============================
    private static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar (escolher profissional)");
            System.out.println("2 - Agendar (busca por especialidade)");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("5 - Listar todas");
            System.out.println("6 - Buscar por CPF");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");
            switch (op) {
                case 1: agendarComProfissional(); break;
                case 2: agendarPorEspecialidade(); break;
                case 3: cancelarConsulta(); break;
                case 4: remarcarConsulta(); break;
                case 5: listarConsultas(); break;
                case 6: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void agendarComProfissional() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        System.out.print("Nome do profissional: ");
        String nomeProf = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();
        String dia = descobrirDiaSemana(data);
        try {
            service.agendarConsulta(cpf, nomeProf, data, horario, dia);
            System.out.println("Consulta agendada com sucesso!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
            tentarSugestao(cpf, nomeProf, data, dia); // jornada 7/19: oferece alternativa
        }
    }

    // Jornada 7/19: sugere um horario livre e, se aceito, agenda nele.
    private static void tentarSugestao(String cpf, String nomeProf, String data, String dia) {
        String sugestao;
        try {
            sugestao = service.sugerirHorario(nomeProf, data, dia);
        } catch (ProfissionalNaoEncontradoException e) {
            return;
        }
        if (sugestao.isEmpty()) return;
        int aceita = lerInteiro("Horario sugerido: " + sugestao + ". Aceita? (1-Sim / 2-Nao): ");
        if (aceita != 1) return;
        try {
            service.agendarConsulta(cpf, nomeProf, data, sugestao, dia);
            System.out.println("Consulta agendada as " + sugestao + "!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Nao foi possivel: " + e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println("Nao foi possivel: " + e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Nao foi possivel: " + e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println("Nao foi possivel: " + e.getMessage());
        }
    }

    private static void agendarPorEspecialidade() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();
        String dia = descobrirDiaSemana(data);
        try {
            Consulta c = service.agendarPorEspecialidade(cpf, esp, data, horario, dia);
            System.out.println("Consulta agendada com " + c.getNomeProfissional() + "!");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cancelarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();
        try {
            Consulta c = service.cancelarConsulta(cpf, data, horario);
            System.out.print("Motivo do cancelamento (ENTER p/ nenhum): ");
            String motivo = sc.nextLine();
            if (!motivo.isEmpty()) c.setMotivo(motivo);
            int horas = lerInteiro("Quantas horas de antecedencia? ");
            if (horas < 2) {
                c.setMulta(50.0);
                System.out.println("Cancelamento com menos de 2h: multa de R$50.00 aplicada.");
            }
            System.out.println("Consulta cancelada.");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void remarcarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data original (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario original (HH:MM): ");
        String horario = sc.nextLine();
        System.out.print("Nova data (DD/MM/AAAA): ");
        String novaData = sc.nextLine();
        System.out.print("Novo horario (HH:MM): ");
        String novoHorario = sc.nextLine();
        String novoDia = descobrirDiaSemana(novaData);
        try {
            service.remarcarConsulta(cpf, data, horario, novaData, novoHorario, novoDia);
            System.out.println("Consulta remarcada com sucesso!");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarConsultas() {
        List<Consulta> consultas = service.getConsultas();
        if (consultas.isEmpty()) { System.out.println("Nenhuma consulta."); return; }
        for (int i = 0; i < consultas.size(); i++) {            // R10 (List): get(index)
            System.out.println("[" + i + "] " + consultas.get(i).exibirResumo());
        }
    }

    private static void buscarConsultasPorPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        boolean achou = false;
        List<Consulta> consultas = service.getConsultas();
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getCpfPaciente().equals(cpf)) {
                System.out.println("[" + i + "] " + consultas.get(i).exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma consulta encontrada.");
    }

    // ============================== ATENDIMENTOS ==============================
    private static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");
            if (op == 1) registrarAtendimento();
        }
    }

    private static void registrarAtendimento() {
        int idx = lerInteiro("Indice da consulta: ");
        System.out.print("Observacoes: ");
        String obs = sc.nextLine();
        int tipo = lerInteiro("Tipo de registro (1-So obs / 2-Com diagnostico / 3-Completo): ");
        String diag = "";
        String[] procs = new String[0];
        if (tipo >= 2) {
            System.out.print("Diagnostico: ");
            diag = sc.nextLine();
        }
        if (tipo == 3) {
            int qtd = lerInteiro("Quantos procedimentos? ");
            procs = new String[qtd];
            for (int i = 0; i < qtd; i++) {
                System.out.print("Procedimento " + (i + 1) + ": ");
                procs[i] = sc.nextLine();
            }
        }
        try {
            Atendimento at = service.registrarAtendimento(idx, obs, diag, procs);
            System.out.println("\n--- RESUMO DO ATENDIMENTO ---");
            System.out.println(at.exibirResumo());
            System.out.println("Consulta marcada como realizada.");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            // R9: finally com proposito (operacao 1 de 2)
            System.out.println("--- Operacao de atendimento finalizada ---");
        }
    }

    // ============================== PAGAMENTOS ==============================
    private static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Registrar pagamento");
            System.out.println("2 - Listar pagamentos");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");
            switch (op) {
                case 1: registrarPagamento(); break;
                case 2: listarPagamentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void registrarPagamento() {
        int idx = lerInteiro("Indice da consulta (ver em Consultas > Listar): ");
        try {
            // o pagamento parte da consulta; o valor base vem do profissional
            Consulta c = service.buscarConsultaPorIndice(idx);
            Profissional prof = service.buscarProfissional(c.getNomeProfissional());
            double valorBase = prof.getValorConsulta();
            System.out.println("Consulta de " + prof.getEspecialidade()
                + " | Valor base: R$ " + String.format("%.2f", valorBase));
            System.out.print("Tipo (dinheiro/pix/cartao/convenio): ");
            String tipo = sc.nextLine();

            Pagamento p;
            if (tipo.equals("dinheiro") || tipo.equals("pix")) {
                p = new PagamentoDinheiro(valorBase);
            } else if (tipo.equals("cartao")) {
                int parc = lerInteiro("Parcelas (1 a 6): ");
                p = new PagamentoCartao(valorBase, parc);
            } else if (tipo.equals("convenio")) {
                // convenio e especialidade vem do paciente/consulta (nao sao digitados)
                Paciente pac = service.buscarPaciente(c.getCpfPaciente());
                Convenio conv = pac.getConvenio();
                if (conv == null) {
                    throw new PagamentoInvalidoException("Paciente nao possui convenio cadastrado.");
                }
                p = new PagamentoConvenio(valorBase, conv, prof.getEspecialidade());
            } else {
                throw new PagamentoInvalidoException("Tipo de pagamento nao reconhecido: " + tipo);
            }
            service.adicionarPagamento(p);
            double vf = Math.round(p.calcularValorFinal() * 100.0) / 100.0;
            System.out.println("Pagamento registrado. Valor final: R$ " + vf);
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (PagamentoInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ConvenioNaoCobreException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            // R9: finally exigido pelo professor (texto exato)
            System.out.println("--- Operacao de pagamento finalizada ---");
        }
    }

    private static void listarPagamentos() {
        List<Pagamento> pagamentos = service.getPagamentos();
        if (pagamentos.isEmpty()) { System.out.println("Nenhum pagamento registrado."); return; }
        for (Pagamento p : pagamentos) {       // R5 ligacao dinamica: calcularValorFinal() de cada tipo
            System.out.println(p.exibirResumo());
        }
    }

    // ============================== RELATORIOS ==============================
    private static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Cadastros (todas as pessoas)");
            System.out.println("2 - Consultas (geral)");
            System.out.println("3 - Consultas por profissional");
            System.out.println("4 - Consultas por periodo");
            System.out.println("5 - Resumo financeiro");
            System.out.println("6 - Cancelamentos");
            System.out.println("7 - Multas aplicadas");
            System.out.println("8 - Exportar dados (backup)");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");
            switch (op) {
                case 1:
                    gerarRelatorioCancelamentos1(service.getTodasPessoas());
                    break;
                case 2:
                    gerarRelatorio(service.getConsultas(), service.getAtendimentos(), null, null);
                    break;
                case 3:
                    System.out.print("Nome do profissional: ");
                    gerarRelatorio(service.getConsultas(), service.getAtendimentos(), sc.nextLine());
                    break;
                case 4:
                    System.out.print("Data inicio (DD/MM/AAAA): ");
                    String ini = sc.nextLine();
                    System.out.print("Data fim (DD/MM/AAAA): ");
                    String fim = sc.nextLine();
                    gerarRelatorio(service.getConsultas(), service.getAtendimentos(), ini, fim);
                    break;
                case 5:
                    gerarResumoFinanceiro(service.getPagamentos(), service.getConsultas());
                    break;
                case 6:
                    gerarRelatorioCancelamentos(service.getConsultas());
                    break;
                case 7:
                    gerarRelatorioCancelamentos(service.getConsultas());
                    break;
                case 8:
                    exportarDados(service.getConsultas(), service.getAtendimentos(),
                                            service.getPagamentos());
                    break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    private static void gerarRelatorioCancelamentos1(List<Pessoa> todasPessoas) {
        throw new UnsupportedOperationException("Unimplemented method 'gerarRelatorioCancelamentos'");
    }

    private static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos, String nextLine) {
        throw new UnsupportedOperationException("Unimplemented method 'gerarRelatorio'");
    }

    private static void exportarDados(List<Consulta> consultas, List<Atendimento> atendimentos,
            List<Pagamento> pagamentos) {
        throw new UnsupportedOperationException("Unimplemented method 'exportarDados'");
    }

    private static void gerarRelatorioCancelamentos(List<Consulta> consultas) {
        throw new UnsupportedOperationException("Unimplemented method 'gerarRelatorioCancelamentos'");
    }

    private static void gerarResumoFinanceiro(List<Pagamento> pagamentos, List<Consulta> consultas) {
        throw new UnsupportedOperationException("Unimplemented method 'gerarResumoFinanceiro'");
    }

    private static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos, String ini,
            String fim) {
        throw new UnsupportedOperationException("Unimplemented method 'gerarRelatorio'");
    }

    // ============================== UTILITARIOS DE ENTRADA ==============================
    // R9: toda leitura numerica protegida contra NumberFormatException, com re-prompt.
    private static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }

    private static double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero (ex: 150.00).");
            }
        }
    }

    // Lê um convênio pelo nome (ENTER = nenhum). Convenio "nao encontrado" nao aborta o cadastro.
    private static Convenio lerConvenio() {
        System.out.print("Convenio (SaudePlus/VidaMais/BemEstar ou ENTER p/ nenhum): ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) return null;
        try {
            return service.buscarConvenio(nome);
        } catch (OperacaoInvalidaException e) {
            System.out.println("Aviso: " + e.getMessage() + " Seguindo sem convenio.");
            return null;
        }
    }

    // Descobre o dia da semana a partir da data (formula de Zeller) - mantido da base.
    private static String descobrirDiaSemana(String data) {
        try {
            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(3, 5));
            int ano = Integer.parseInt(data.substring(6, 10));
            if (mes < 3) { mes += 12; ano -= 1; }
            int k = ano % 100;
            int j = ano / 100;
            int r = (dia + (13 * (mes + 1)) / 5 + k + k / 4 + j / 4 - 2 * j) % 7;
            if (r < 0) r += 7;
            String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
            return nomes[r];
        } catch (RuntimeException e) {
            return ""; // data malformada -> nenhum profissional atende "" -> HorarioIndisponivel
        }
    }
}
