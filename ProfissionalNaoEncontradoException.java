// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class ProfissionalNaoEncontradoException extends Exception {

    public ProfissionalNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProfissionalNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
