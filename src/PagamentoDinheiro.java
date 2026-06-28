public class PagamentoDinheiro extends Pagamento {
    public PagamentoDinheiro(double valorBase) throws PagamentoInvalidoException {
        super(valorBase); // R3: super (pode lançar PagamentoInvalidoException)
        this.descricao = "Dinheiro/PIX";
    }

    // SOBRESCRITA / LIGAÇÃO DINÂMICA: regra própria = 5% de desconto
    @Override
    public double calcularValorFinal() {
        return valorBase * 0.95;
    }
}
