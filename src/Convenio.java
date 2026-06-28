import java.util.ArrayList;
import java.util.List;

public class Convenio {
    private String nome;
    private double percentualCobertura;     
    private List<String> especialidadesCobertas;

    public Convenio(String nome, double percentualCobertura) {
        this.nome = nome;
        this.percentualCobertura = percentualCobertura;
        this.especialidadesCobertas = new ArrayList<>();
    }

    public Convenio(String nome, double percentualCobertura, List<String> especialidadesCobertas) {
        this.nome = nome;
        this.percentualCobertura = percentualCobertura;
        this.especialidadesCobertas = new ArrayList<>();
        for (String e : especialidadesCobertas) {
            this.especialidadesCobertas.add(e.toLowerCase());
        }
    }

    public void adicionarEspecialidade(String especialidade) {
        especialidadesCobertas.add(especialidade.toLowerCase());
    }

    public boolean cobreEspecialidade(String especialidade) {
        return especialidadesCobertas.contains(especialidade.toLowerCase());
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPercentualCobertura() { return percentualCobertura; }
    public void setPercentualCobertura(double percentualCobertura) { this.percentualCobertura = percentualCobertura; }
    public List<String> getEspecialidadesCobertas() { return especialidadesCobertas; }
}
