import java.util.List;

// R7: implementa Exportavel
public class Atendimento implements Exportavel {

    private int indiceConsulta; 

    // R8 COMPOSIÇÃO: 
    private Prontuario prontuario;

    // R4: SOBRECARGA DE CONSTRUTORES 
    public Atendimento(int indiceConsulta, String data, String observacoes) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(data); 
        prontuario.setObservacoes(observacoes);
    }
    public Atendimento(int indiceConsulta, String data, String observacoes, String diagnostico) {
        this(indiceConsulta, data, observacoes);
        prontuario.setDiagnostico(diagnostico);
    }
    public Atendimento(int indiceConsulta, String data, String observacoes, String diagnostico, String[] procedimentos) {
        this(indiceConsulta, data, observacoes, diagnostico);
        for (String p : procedimentos) {
            if (p != null && !p.isEmpty()) prontuario.adicionarProcedimento(p);
        }
    }

    // R4: SOBRECARGA DE MÉTODOS 
    public void adicionarProcedimento(String procedimento) {
        prontuario.adicionarProcedimento(procedimento);
    }
    public void adicionarProcedimento(String[] procs, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (procs[i] != null && !procs[i].isEmpty()) prontuario.adicionarProcedimento(procs[i]);
        }
    }

   
    public void adicionarObservacao(String texto) {
        String atual = prontuario.getObservacoes();
        prontuario.setObservacoes((atual == null || atual.isEmpty()) ? texto : atual + " | " + texto);
    }

    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Observacoes: ").append(prontuario.getObservacoes());
        if (!prontuario.getDiagnostico().isEmpty()) {
            sb.append("\nDiagnostico: ").append(prontuario.getDiagnostico());
        }
        List<String> procs = prontuario.getProcedimentos();
        if (!procs.isEmpty()) {
            sb.append("\nProcedimentos: ");
            for (int i = 0; i < procs.size(); i++) {
                sb.append(procs.get(i));
                if (i < procs.size() - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }

    // R7: contrato Exportavel
    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("ATENDIMENTO|consulta=").append(indiceConsulta)
          .append("|data=").append(prontuario.getData())
          .append("|obs=").append(prontuario.getObservacoes())
          .append("|diag=").append(prontuario.getDiagnostico().isEmpty() ? "-" : prontuario.getDiagnostico())
          .append("|procedimentos=");
        List<String> procs = prontuario.getProcedimentos();
        if (procs.isEmpty()) {
            sb.append("-");
        } else {
            for (int i = 0; i < procs.size(); i++) {
                sb.append(procs.get(i));
                if (i < procs.size() - 1) sb.append(",");
            }
        }
        return sb.toString();
    }

    public Prontuario getProntuario() { return prontuario; }
    public int getIndiceConsulta() { return indiceConsulta; }
    public String getDiagnostico() { return prontuario.getDiagnostico(); } // usado pelo Relatorio
}
