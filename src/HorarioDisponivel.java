public class HorarioDisponivel {
    private String diaSemana; // segunda, terca, quarta...
    private String turno;     // manha, tarde

    public HorarioDisponivel(String diaSemana, String turno) {
        this.diaSemana = diaSemana;
        this.turno = turno;
    }

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    @Override
    public String toString() {
        return diaSemana + " (" + turno + ")";
    }
}