public class Fisioterapeuta extends Profissional {
    private int totalSessoesPrevistas; 

    public Fisioterapeuta(String nome, String cpf, String telefone, String dataNascimento,
                          String registroProfissional, double valorConsulta, int totalSessoesPrevistas) {
        super(nome, cpf, telefone, dataNascimento, "fisioterapia", registroProfissional, valorConsulta); 
        setTotalSessoesPrevistas(totalSessoesPrevistas);
    }

    @Override
    public String exibirResumo() {
       return "[FISIOTERAPEUTA] " + getNome() + " | " + descricaoBase()
     + " | Sessoes previstas: " + totalSessoesPrevistas;

    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) { 
        atendimento.adicionarObservacao("Fisioterapia: plano de " + totalSessoesPrevistas + " sessoes.");
    }

    public String planoResumido() {
        return "Plano fisioterapico com " + totalSessoesPrevistas + " sessoes.";
    }

    public int getTotalSessoesPrevistas() { return totalSessoesPrevistas; }
    public void setTotalSessoesPrevistas(int totalSessoesPrevistas) {
        if (totalSessoesPrevistas < 0) {
            throw new IllegalArgumentException("Total de sessoes nao pode ser negativo.");
        }
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }
}




