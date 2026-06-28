public abstract class Pessoa {

    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String dataNascimento;

    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        this.nome = nome;
        setCpf(cpf);                 
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    // R6: MÉTODO ABSTRATO 
    public abstract String exibirResumo();

    // R6: MÉTODO CONCRETO 
    public String identificacao() {
        return nome + " (CPF: " + cpf + ")";
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    // R1: SETTER COM VALIDAÇÃO — recusa CPF vazio/nulo.
    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF nao pode ser vazio.");
        }
        this.cpf = cpf;
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
}