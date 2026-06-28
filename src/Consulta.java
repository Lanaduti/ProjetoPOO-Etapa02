public class Consulta implements Agendavel, Exportavel {

    private String cpfPaciente;
    private String nomeProfissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;  
    private double multa;   
    private String motivo;  

    // R4: SOBRECARGA DE CONSTRUTORES 
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this.cpfPaciente = cpfPaciente;
        this.nomeProfissional = nomeProfissional;
        this.data = data;
        this.horario = horario;
        this.tipo = "inicial";
        this.status = "agendada";
        this.multa = 0.0;
        this.motivo = "";
    }
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this(cpfPaciente, nomeProfissional, data, horario);
        this.tipo = (tipo == null || tipo.isEmpty()) ? "inicial" : tipo;
    }
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo, String status) {
        this(cpfPaciente, nomeProfissional, data, horario, tipo);
        this.status = status;
    }

    // ----- R7: contrato Agendavel -----
    @Override
    public void agendar() {
        this.status = "agendada";
    }

    @Override
    public void cancelar() throws OperacaoInvalidaException {
        if (status.equals("realizada")) {
            throw new OperacaoInvalidaException("Nao e possivel cancelar: consulta ja foi realizada.");
        }
        if (status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Nao e possivel cancelar: consulta ja esta cancelada.");
        }
        this.status = "cancelada";
    }

    @Override
    public void remarcar() throws OperacaoInvalidaException {
        if (!status.equals("agendada")) {
            throw new OperacaoInvalidaException("So e possivel remarcar consultas agendadas. Status: " + status);
        }
        this.status = "remarcada"; // a nova consulta e criada pelo servico (preserva o historico)
    }

    public void realizar() throws OperacaoInvalidaException {
        if (!status.equals("agendada")) {
            throw new OperacaoInvalidaException("So e possivel registrar atendimento em consulta agendada. Status: " + status);
        }
        this.status = "realizada";
    }

    // ----- R7: contrato Exportavel -----
    @Override
    public String exportarDados() {
        return "CONSULTA|paciente=" + cpfPaciente
             + "|profissional=" + nomeProfissional
             + "|data=" + data + "|horario=" + horario
             + "|tipo=" + tipo + "|status=" + status;
    }

   
    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
             + " | Data: " + data + " | Hora: " + horario
             + " | Tipo: " + tipo + " | Status: " + status
             + (multa > 0 ? " | Multa: R$" + multa : "")
             + (motivo != null && !motivo.isEmpty() ? " | Motivo: " + motivo : "");
    }

    public String getCpfPaciente() { return cpfPaciente; }
    public String getNomeProfissional() { return nomeProfissional; }
    public String getData() { return data; }
    public String getHorario() { return horario; }
    public String getTipo() { return tipo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getMulta() { return multa; }
    public void setMulta(double multa) { this.multa = multa; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}

