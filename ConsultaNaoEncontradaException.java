// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class ConsultaNaoEncontradaException extends Exception {

    public ConsultaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ConsultaNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
