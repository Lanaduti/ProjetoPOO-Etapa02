public class ClinicoGeral extends Profissional {
    private String encaminhamento; 

    public ClinicoGeral(String nome, String cpf, String telefone, String dataNascimento,
                        String registroProfissional, double valorConsulta, String encaminhamento) {
        super(nome, cpf, telefone, dataNascimento, "clinica geral", registroProfissional, valorConsulta);
        this.encaminhamento = encaminhamento;
    }

    @Override
    public String exibirResumo() { 
        return "[CLINICO GERAL] " + nome + " | " + descricaoBase()
             + " | Encaminhamento: " + (encaminhamento == null || encaminhamento.isEmpty() ? "Nenhum" : encaminhamento);
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) { 
        atendimento.adicionarObservacao("Clinica geral: encaminhamento -> "
            + (encaminhamento == null || encaminhamento.isEmpty() ? "nenhum" : encaminhamento));
    }

    public String getEncaminhamento() { return encaminhamento; }
    public void setEncaminhamento(String encaminhamento) { this.encaminhamento = encaminhamento; }
}
