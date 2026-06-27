public class Fisioterapeuta extends Profissional {

    private int sessoes;

    public Fisioterapeuta(String nome, String cpf, String telefone, String dataNascimento, 
                          String registro, double valor, int sessoes) {
        super(nome, cpf, telefone, dataNascimento, registro, valor);
        this.sessoes = sessoes;
    }

    @Override
    public void exibirResumo() {
        System.out.println("--- Fisioterapeuta ---");
        System.out.println("Nome: " + getNome());
        System.out.println("Registro: " + getRegistroProfissional());
        System.out.println("Sessões previstas: " + this.sessoes);
        System.out.println("Valor da Consulta: R$" + getValorConsulta());
    }
}
