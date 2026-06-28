import colecoes.Profissional;

public class ClinicoGeral extends Profissional {
    private String crmRegional;

    public ClinicoGeral(String nome, String registroProfissional, double valorConsulta, String crmRegional) {
        // Passa os dados certos para o construtor da classe do professor
        super(nome, "clinica geral", registroProfissional, valorConsulta);
        this.crmRegional = crmRegional;
    }

    @Override
    public String exibirResumo() {
        // Usa o resumo do professor e adiciona o CRM Regional do Clínico Geral
        return super.exibirResumo() + " | CRM Regional: " + this.crmRegional;
    }
}