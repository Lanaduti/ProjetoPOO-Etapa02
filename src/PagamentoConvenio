public class PagamentoConvenio extends Pagamento {
    private Convenio convenio;
    private String especialidade;

    public PagamentoConvenio(double valorBase, Convenio convenio, String especialidade)
            throws PagamentoInvalidoException, ConvenioNaoCobreException {
        super(valorBase);
        if (convenio == null) {
            throw new PagamentoInvalidoException("Convenio nao informado.");
        }
     
        if (!convenio.cobreEspecialidade(especialidade)) {
            throw new ConvenioNaoCobreException("Convenio " + convenio.getNome()
                + " nao cobre a especialidade '" + especialidade + "'.");
        }
        this.convenio = convenio;
        this.especialidade = especialidade;
        this.descricao = "Convenio " + convenio.getNome();
    }

  
    @Override
    public double calcularValorFinal() {
        return valorBase * (1 - convenio.getPercentualCobertura());
    }

    public Convenio getConvenio() { return convenio; }
    public String getEspecialidade() { return especialidade; }
}
