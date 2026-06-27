public class Psicologo extends Profissional {
    private String abordagem;

    public Psicologo(String nome, String registroProfissional, double valorConsulta, String abordagem) {
        // Passa os parâmetros certos para a classe do professor ("psicologia")
        super(nome, "psicologia", registroProfissional, valorConsulta);
        this.abordagem = abordagem;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Abordagem: " + this.abordagem;
    }
}