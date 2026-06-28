public class Psicologo extends Profissional {
    private String abordagem; 

    public Psicologo(String nome, String cpf, String telefone, String dataNascimento,
                     String registroProfissional, double valorConsulta, String abordagem) {
        super(nome, cpf, telefone, dataNascimento, "psicologia", registroProfissional, valorConsulta);
        this.abordagem = abordagem;
    }

    @Override
    public String exibirResumo() { 
        return "[PSICOLOGO] " + nome + " | " + descricaoBase() + " | Abordagem: " + abordagem;
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) { 
        atendimento.adicionarObservacao("Psicologia: abordagem " + abordagem + ".");
    }

    public String definirAbordagem(String nova) { this.abordagem = nova; return abordagem; } 
    public String getAbordagem() { return abordagem; }
    public void setAbordagem(String abordagem) { this.abordagem = abordagem; }
}
