public class Nutricionista extends Profissional {
    private String planoAlimentar; // R3: atributo próprio

    public Nutricionista(String nome, String cpf, String telefone, String dataNascimento,
                         String registroProfissional, double valorConsulta, String planoAlimentar) {
        super(nome, cpf, telefone, dataNascimento, "nutricao", registroProfissional, valorConsulta);
        this.planoAlimentar = planoAlimentar;
    }

    @Override
    public String exibirResumo() { // SOBRESCRITA
        return "[NUTRICIONISTA] " + nome + " | " + descricaoBase() + " | Plano: " + planoAlimentar;
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) { // SOBRESCRITA
        atendimento.adicionarObservacao("Nutricao: plano alimentar -> " + planoAlimentar);
    }

    public String getPlanoAlimentar() { return planoAlimentar; }
    public void setPlanoAlimentar(String planoAlimentar) { this.planoAlimentar = planoAlimentar; }
}
