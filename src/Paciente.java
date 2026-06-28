public class Paciente extends Pessoa {

    private int idade;
    private boolean ativo;

    // R8 ASSOCIAÇÃO: Paciente conhece um Convenio; ambos existem independentemente
    // (pode não ter convênio; o convênio existe sem o paciente).
    private Convenio convenio;

    // R4: SOBRECARGA DE CONSTRUTORES 
    public Paciente(String nome, String cpf) {
        super(nome, cpf, "", "");
        this.idade = 0;
        this.ativo = true;
        this.convenio = null;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone, "");
        setIdade(idade);
        this.ativo = true;
        this.convenio = null;
    }

    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, telefone, "");
        setIdade(idade);
        this.ativo = true;
        this.convenio = convenio; 
    }

    @Override
    public String exibirResumo() {
        String status = ativo ? "Sim" : "Nao";
        return "[PACIENTE] Nome: " + nome + " | CPF: " + cpf
             + " | Idade: " + idade
             + " | Tel: " + telefone
             + " | Convenio: " + (convenio == null ? "Nenhum" : convenio.getNome())
             + " | Ativo: " + status;
    }

    // R4: SOBRECARGA DE MÉTODOS 
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        this.telefone = telefone;
    }
    public void complementar(int idade, String telefone, Convenio convenio) {
        setIdade(idade);
        this.telefone = telefone;
        this.convenio = convenio;
    }

    public void desativar() { this.ativo = false; }
    public void ativar() { this.ativo = true; }

    public int getIdade() { return idade; }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade nao pode ser negativa.");
        }
        this.idade = idade;
    }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Convenio getConvenio() { return convenio; }
    public void setConvenio(Convenio convenio) { this.convenio = convenio; }
}

