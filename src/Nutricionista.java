public class Nutricionista extends Profissional {
    private String crn;

    public Nutricionista(String nome, String registroProfissional, double valorConsulta, String crn) {

        super(nome, "nutricao", registroProfissional, valorConsulta);
        this.crn = crn;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | CRN: " + this.crn;
    }
}
