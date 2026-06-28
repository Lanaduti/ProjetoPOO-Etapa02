public abstract class Pessoa {

    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = "";
        this.dataNascimento = "";
    }

    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isBlank()) {
            this.nome = nome;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!cpf.isBlank()) {
            this.cpf = cpf;
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public abstract String exibirResumo();
}