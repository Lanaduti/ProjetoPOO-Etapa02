// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class ConvenioNaoCobreException extends Exception {

    public ConvenioNaoCobreException(String mensagem) {
        super(mensagem);
    }

    public ConvenioNaoCobreException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
