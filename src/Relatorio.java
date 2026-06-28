import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    // RELATORIO DE CADASTROS (lista unificada de Pessoa).
    // R5 LIGAÇÃO DINÂMICA: o exibirResumo() executado depende do tipo REAL do objeto.
    // DYNAMIC CASTING: instanceof + cast para mostrar dados extras de Paciente/Profissional.
    public static void gerarRelatorioPessoas(List<Pessoa> pessoas) {
        System.out.println("\n=== RELATORIO DE CADASTROS ===");
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
            return;
        }
        int qtdPacientes = 0;
        int qtdProfissionais = 0;
        for (Pessoa p : pessoas) {
            // LIGAÇÃO DINÂMICA: chama o exibirResumo() do tipo real (Paciente, Fisioterapeuta, ...)
            System.out.println(p.exibirResumo());

            // DYNAMIC CASTING: descobrir o tipo concreto para info adicional
            if (p instanceof Paciente) {
                qtdPacientes++;
                Paciente pac = (Paciente) p;
                if (pac.getConvenio() != null) {
                    System.out.println("   -> Convenio cobre: " + pac.getConvenio().getEspecialidadesCobertas());
                }
            } else if (p instanceof Profissional) {
                qtdProfissionais++;
                Profissional prof = (Profissional) p;
                System.out.println("   -> Dias de atendimento: " + prof.getHorariosDisponiveis());
            }
            System.out.println("---");
        }
        // Jornada 15: totais ao final
        System.out.println("TOTAIS: " + qtdPacientes + " paciente(s), " + qtdProfissionais + " profissional(is).");
    }

    // R4: SOBRECARGA — relatório geral de consultas
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos) {
        System.out.println("\n=== RELATORIO GERAL DE CONSULTAS ===");
        if (consultas.isEmpty()) { System.out.println("Nenhuma consulta."); return; }
        for (int i = 0; i < consultas.size(); i++) {
            imprimirConsulta(i, consultas.get(i), atendimentos);
        }
    }

    // R4: SOBRECARGA — filtra por profissional
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos,
                                      String nomeProfissional) {
        System.out.println("\n=== RELATORIO - PROF: " + nomeProfissional + " ===");
        boolean achou = false;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getNomeProfissional().equalsIgnoreCase(nomeProfissional)) {
                imprimirConsulta(i, consultas.get(i), atendimentos);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma consulta para esse profissional.");
    }

    // R4: SOBRECARGA — filtra por período
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos,
                                      String dataInicio, String dataFim) {
        System.out.println("\n=== RELATORIO - " + dataInicio + " a " + dataFim + " ===");
        boolean achou = false;
        for (int i = 0; i < consultas.size(); i++) {
            if (estaNoIntervalo(consultas.get(i).getData(), dataInicio, dataFim)) {
                imprimirConsulta(i, consultas.get(i), atendimentos);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma consulta no periodo.");
    }

    // RESUMO FINANCEIRO — soma os valores via calcularValorFinal() (polimórfico) + multas das consultas.
    public static void gerarResumoFinanceiro(List<Pagamento> pagamentos, List<Consulta> consultas) {
        int realizadas = 0, canceladas = 0;
        double totalFaturado = 0, totalEmMultas = 0;

        for (Consulta c : consultas) {
            if (c.getStatus().equals("realizada")) realizadas++;
            if (c.getStatus().equals("cancelada")) canceladas++;
            totalEmMultas += c.getMulta();
        }
        // LIGAÇÃO DINÂMICA: cada Pagamento calcula seu valor final de um jeito.
        for (Pagamento p : pagamentos) {
            totalFaturado += p.calcularValorFinal();
        }

        System.out.println("\n=== RESUMO FINANCEIRO ===");
        System.out.println("Consultas realizadas: " + realizadas);
        System.out.println("Cancelamentos: " + canceladas);
        System.out.println("Total faturado (pagamentos): R$ " + arredondar(totalFaturado));
        System.out.println("Total em multas: R$ " + arredondar(totalEmMultas));
    }

    // Jornada 13: relatorio de cancelamentos
    public static void gerarRelatorioCancelamentos(List<Consulta> consultas) {
        System.out.println("\n=== RELATORIO DE CANCELAMENTOS ===");
        boolean achou = false;
        for (Consulta c : consultas) {
            if (c.getStatus().equals("cancelada")) {
                System.out.println(c.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum cancelamento registrado.");
    }

    // Jornada 13: relatorio de multas aplicadas
    public static void gerarRelatorioMultas(List<Consulta> consultas) {
        System.out.println("\n=== RELATORIO DE MULTAS ===");
        double total = 0;
        boolean achou = false;
        for (Consulta c : consultas) {
            if (c.getMulta() > 0) {
                System.out.println(c.exibirResumo());
                total += c.getMulta();
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma multa aplicada.");
        else System.out.println("Total em multas: R$ " + arredondar(total));
    }

    // Jornada 26: exporta de forma UNIFORME todos os objetos Exportavel (consultas, atendimentos, pagamentos).
    public static void exportarDados(List<Consulta> consultas, List<Atendimento> atendimentos,
                                     List<Pagamento> pagamentos) {
        // R7: polimorfismo via interface — uma unica colecao de Exportavel com tipos diferentes.
        List<Exportavel> exportaveis = new ArrayList<>();
        exportaveis.addAll(consultas);
        exportaveis.addAll(atendimentos);
        exportaveis.addAll(pagamentos);
        System.out.println("\n=== EXPORTACAO DE DADOS (" + exportaveis.size() + " registros) ===");
        if (exportaveis.isEmpty()) { System.out.println("Nada a exportar."); return; }
        for (Exportavel e : exportaveis) {
            System.out.println(e.exportarDados()); // cada tipo fornece sua representacao textual
        }
    }

    // ---- helpers (mantidos da base) ----
    private static void imprimirConsulta(int indice, Consulta c, List<Atendimento> atendimentos) {
        System.out.println("[" + indice + "] " + c.exibirResumo());
        String diag = buscarDiagnostico(indice, atendimentos);
        if (!diag.isEmpty()) System.out.println("   Diagnostico: " + diag);
        System.out.println("---");
    }

    private static String buscarDiagnostico(int indiceConsulta, List<Atendimento> atendimentos) {
        for (Atendimento a : atendimentos) {
            if (a.getIndiceConsulta() == indiceConsulta) return a.getDiagnostico();
        }
        return "";
    }

    public static boolean estaNoIntervalo(String data, String inicio, String fim) {
        int v = converterDataParaNumero(data);
        return v >= converterDataParaNumero(inicio) && v <= converterDataParaNumero(fim);
    }

    private static int converterDataParaNumero(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));
        return ano * 10000 + mes * 100 + dia;
    }

    private static double arredondar(double v) { return Math.round(v * 100.0) / 100.0; }
}
