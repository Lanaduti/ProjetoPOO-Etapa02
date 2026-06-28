public class PagamentoCartao extends Pagamento {
    private int parcelas;

    public PagamentoCartao(double valorBase, int parcelas) throws PagamentoInvalidoException {
        super(valorBase);
        // Regra do professor: cartão de 1 a 6 parcelas
        if (parcelas < 1 || parcelas > 6) {
            throw new PagamentoInvalidoException("Parcelas devem estar entre 1 e 6. Informado: " + parcelas);
        }
        this.parcelas = parcelas;
        this.descricao = "Cartao (" + parcelas + "x)";
    }

    // SOBRESCRITA / LIGAÇÃO DINÂMICA: acima de 3x, 2,5% por parcela extra
    @Override
    public double calcularValorFinal() {
        double valor = valorBase;
        if (parcelas > 3) {
            int extras = parcelas - 3;
            valor = valorBase * (1 + 0.025 * extras);
        }
        return valor;
    }

    public int getParcelas() { return parcelas; }
}
