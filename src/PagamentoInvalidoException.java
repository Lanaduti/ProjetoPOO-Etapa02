// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class PagamentoInvalidoException extends Exception {

    public PagamentoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public PagamentoInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
