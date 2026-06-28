package colecoes;
public class Fisioterapeuta extends Profissional {
    private String crefito;

    public Fisioterapeuta(String nome, String registroProfissional, double valorConsulta, String crefito) {
        // Passa os dados certos para o construtor do professor
        super(nome, "fisioterapia", registroProfissional, valorConsulta);
        this.crefito = crefito;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | CREFITO: " + this.crefito;
    }
}