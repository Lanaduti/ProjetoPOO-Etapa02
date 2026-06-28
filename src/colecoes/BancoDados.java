package colecoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BancoDados {

    public static ArrayList<Paciente> pacientes =
            new ArrayList<>();

    public static ArrayList<Profissional> profissionais =
            new ArrayList<>();

    public static ArrayList<Consulta> ponsultas =
            new ArrayList<>();

    public static HashMap<String, Paciente> buscaCpf =
            new HashMap<>();

    public static HashSet<String> cpfs =
            new HashSet<>();

}