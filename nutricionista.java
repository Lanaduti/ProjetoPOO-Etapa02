public class Nutricionista extends Profissional {
    private String especialidadeDietetica;

    public Nutricionista(String nome, String cpf, String telefone, String dataNascimento, 
                         String registro, double valor, String especialidade) {
        super(nome, cpf, telefone, dataNascimento, registro, valor);
        this.especialidadeDietetica = especialidade;
    }

    @Override
    public void exibirResumo() {
        System.out.println("--- Nutricionista ---");
        System.out.println("Nome: " + getNome());
        System.out.println("Registro: " + getRegistroProfissional());
        System.out.println("Especialidade: " + this.especialidadeDietetica);
        System.out.println("Valor: R$" + getValorConsulta());
    }
}
