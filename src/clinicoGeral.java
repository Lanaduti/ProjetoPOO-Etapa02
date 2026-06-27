public class ClinicoGeral extends Profissional {
    private String crmRegional;

    public ClinicoGeral(String nome, String cpf, String telefone, String dataNascimento, 
                        String registro, double valor, String crmRegional) {
        super(nome, cpf, telefone, dataNascimento, registro, valor);
        this.crmRegional = crmRegional;
    }

    @Override
    public void exibirResumo() {
        System.out.println("--- Clínico Geral ---");
        System.out.println("Nome: " + getNome());
        System.out.println("Registro: " + getRegistroProfissional());
        System.out.println("CRM Regional: " + this.crmRegional);
        System.out.println("Valor: R$" + getValorConsulta());
    }
}
