public abstract class Pagamento implements Exportavel {

    protected double valorBase;
    protected String descricao;

    public Pagamento(double valorBase) throws PagamentoInvalidoException {
        if (valorBase < 0) {
            throw new PagamentoInvalidoException("Valor do pagamento nao pode ser negativo: " + valorBase);
        }
        this.valorBase = valorBase;
        this.descricao = "Pagamento";
    }

    // R6/R5: MÉTODO ABSTRATO — cada forma calcula o valor final de um jeito.
    public abstract double calcularValorFinal();

    // R6: MÉTODO CONCRETO — comum a todos. 
    public String exibirResumo() {
        double v = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "[PAGAMENTO] Tipo: " + descricao
             + " | Base: R$ " + String.format("%.2f", valorBase)
             + " | Final: R$ " + v;
    }

    // R7: contrato Exportavel
    @Override
    public String exportarDados() {
        return "PAGAMENTO|tipo=" + descricao
             + "|base=" + String.format("%.2f", valorBase)
             + "|final=" + String.format("%.2f", calcularValorFinal());
    }

    public double getValorBase() { return valorBase; }
    public String getDescricao() { return descricao; }
}
public abstract class Pagamento implements Exportavel {

    protected double valorBase;
    protected String descricao;

    public Pagamento(double valorBase) throws PagamentoInvalidoException {
        if (valorBase < 0) {
            throw new PagamentoInvalidoException("Valor do pagamento nao pode ser negativo: " + valorBase);
        }
        this.valorBase = valorBase;
        this.descricao = "Pagamento";
    }

    // R6/R5: MÉTODO ABSTRATO — cada forma calcula o valor final de um jeito.
    public abstract double calcularValorFinal();

    // R6: MÉTODO CONCRETO — comum a todos. 
    public String exibirResumo() {
        double v = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "[PAGAMENTO] Tipo: " + descricao
             + " | Base: R$ " + String.format("%.2f", valorBase)
             + " | Final: R$ " + v;
    }

    // R7: contrato Exportavel
    @Override
    public String exportarDados() {
        return "PAGAMENTO|tipo=" + descricao
             + "|base=" + String.format("%.2f", valorBase)
             + "|final=" + String.format("%.2f", calcularValorFinal());
    }

    public double getValorBase() { return valorBase; }
    public String getDescricao() { return descricao; }
}

