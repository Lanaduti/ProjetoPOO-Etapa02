import java.util.ArrayList;
import java.util.List;


public abstract class Profissional extends Pessoa {

    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;

    // R8 AGREGAÇÃO: lista de horários; os horários existem independentemente do profissional.
    private List<HorarioDisponivel> horariosDisponiveis;

    public Profissional(String nome, String cpf, String telefone, String dataNascimento,
                        String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf, telefone, dataNascimento); 
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        setValorConsulta(valorConsulta);           
        this.horariosDisponiveis = new ArrayList<>();
    }

    public abstract void registrarEspecifico(Atendimento atendimento);

    // R2: MÉTODO PROTEGIDO — usado APENAS pelas especializações para montar exibirResumo().
    protected String descricaoBase() {
        return "Espec: " + especialidade
             + " | Reg: " + registroProfissional
             + " | Valor: R$ " + String.format("%.2f", valorConsulta);
    }

    public boolean atendeNoDia(String diaSemana) {
        for (HorarioDisponivel h : horariosDisponiveis) {
            if (h.getDiaSemana().equalsIgnoreCase(diaSemana)) return true;
        }
        return false;
    }

    // R4: SOBRECARGA DE MÉTODO — duas formas de adicionar horário (agregação)
    public void adicionarHorario(HorarioDisponivel horario) { horariosDisponiveis.add(horario); }
    public void adicionarHorario(String dia, String turno) { horariosDisponiveis.add(new HorarioDisponivel(dia, turno)); }

    public static boolean especialidadeValida(String esp) {
        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;
        return false;
    }

    public List<HorarioDisponivel> getHorariosDisponiveis() { return horariosDisponiveis; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getRegistroProfissional() { return registroProfissional; }
    public void setRegistroProfissional(String registroProfissional) { this.registroProfissional = registroProfissional; }

    public double getValorConsulta() { return valorConsulta; }

    // R1: SETTER COM VALIDAÇÃO — recusa valor negativo.
    public void setValorConsulta(double valorConsulta) {
        if (valorConsulta < 0) {
            throw new IllegalArgumentException("Valor da consulta nao pode ser negativo.");
        }
        this.valorConsulta = valorConsulta;
    }
}

