
import colecoes.Profissional;

public class Nutricionista extends Profissional {
    private String crn;

    public Nutricionista(String nome, String registroProfissional, double valorConsulta, String crn) {
        // Passa os parâmetros certos para a classe do professor ("nutricao")
        super(nome, "nutricao", registroProfissional, valorConsulta);
        this.crn = crn;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | CRN: " + this.crn;
    }
}