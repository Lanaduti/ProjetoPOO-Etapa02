// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class PacienteInativoException extends Exception {

    public PacienteInativoException(String mensagem) {
        super(mensagem);
    }

    public PacienteInativoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
