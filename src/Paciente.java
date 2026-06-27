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

    // construtor com todos os dados
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.convenioNome = convenioNome;
        this.ativo = true;
    }

    // atualiza so idade e telefone
    public void complementar(int idade, String telefone) {
        this.idade = idade;
        this.telefone = telefone;
    }

    // atualiza tudo incluindo convenio
    public void complementar(int idade, String telefone, String convenioNome) {
        this.idade = idade;
        this.telefone = telefone;
        this.convenioNome = convenioNome;
    }

    public void desativar() {
        this.ativo = false;
    }

    public String exibirResumo() {
        String status = "Sim";
        if (!ativo) {
            status = "Nao";
        }
        return "Nome: " + nome + " | CPF: " + cpf + " | Idade: " + idade
                + " | Tel: " + telefone + " | Convenio: " + convenioNome
                + " | Ativo: " + status;
    }
}
