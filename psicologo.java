public class Psicologo extends Profissional {
    private String abordagem;

    public Psicologo(String nome, String cpf, String telefone, String dataNascimento, 
                     String registro, double valor, String abordagem) {
        super(nome, cpf, telefone, dataNascimento, registro, valor);
        this.abordagem = abordagem;
    }

    @Override
    public void exibirResumo() {
        System.out.println("--- Psicólogo ---");
        System.out.println("Nome: " + getNome());
        System.out.println("Registro: " + getRegistroProfissional());
        System.out.println("Abordagem: " + this.abordagem);
        System.out.println("Valor: R$" + getValorConsulta());
    }
}
