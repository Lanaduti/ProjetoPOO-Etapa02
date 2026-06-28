public interface Agendavel {
    void agendar();
    void cancelar() throws OperacaoInvalidaException;
    void remarcar() throws OperacaoInvalidaException;
}