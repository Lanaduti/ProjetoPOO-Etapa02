import java.util.ArrayList;
import java.util.List;

public class Prontuario {
    private String observacoes;
    private String diagnostico;
    private List<String> procedimentos; // R10: list dentro do prontuário
    private String data;

    Prontuario(String data) {
        this.data = data;
        this.observacoes = "";
        this.diagnostico = "";
        this.procedimentos = new ArrayList<>();
    }

    void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    void adicionarProcedimento(String procedimento) { procedimentos.add(procedimento); }

    public String getObservacoes() { return observacoes; }
    public String getDiagnostico() { return diagnostico; }
    public List<String> getProcedimentos() { return procedimentos; }
    public String getData() { return data; }
}