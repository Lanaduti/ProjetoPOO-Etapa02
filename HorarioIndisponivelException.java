// R9: Exceção personalizada VERIFICADA (estende Exception => checked exception).
// SOBRECARGA de construtor: um só com a mensagem e outro com mensagem + causa.
public class HorarioIndisponivelException extends Exception {

    public HorarioIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public HorarioIndisponivelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
