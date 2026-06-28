// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class OperacaoInvalidaException extends Exception {

    public OperacaoInvalidaException(String mensagem) {
        super(mensagem);
    }

    public OperacaoInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
