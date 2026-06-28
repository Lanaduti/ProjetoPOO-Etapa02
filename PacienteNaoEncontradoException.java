// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class PacienteNaoEncontradoException extends Exception {

    public PacienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PacienteNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
