public class Paciente extends Pessoa {
    private int idade;
    private String convenioNome;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        super(nome, cpf); // Chama o construtor da classe Pessoa
        this.idade = 0;
        this.telefone = ""; // Acessível se for protected em Pessoa
        this.convenioNome = "";
        this.ativo = true;
    }

   public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf);
        this.telefone = telefone;
        this.idade = idade;
        this.convenioNome = convenioNome;
        this.ativo = true;
    }
    }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getConvenioNome() { return convenioNome; }
    public void setConvenioNome(String convenioNome) { this.convenioNome = convenioNome; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public void complementar(int idade, String telefone) {
        this.idade = idade;
        this.telefone = telefone;
    }

    public void complementar(int idade, String telefone, String convenioNome) {
        this.idade = idade;
        this.telefone = telefone;
        this.convenioNome = convenioNome;
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String exibirResumo() {
        String status = ativo ? "Sim" : "Nao";
        return super.exibirResumo() + " | Idade: " + idade
                + " | Convenio: " + convenioNome
                + " | Ativo: " + status;
    }
}
