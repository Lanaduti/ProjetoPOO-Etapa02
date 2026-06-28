package exceptions;

public class PacienteNaoEncontradoException
extends Exception{

public PacienteNaoEncontradoException(){

super(
"Paciente nao foi encontrado."
);

}

}