package dados;

public abstract class Drone {

    private int codigo;
    private double custoFixo;
    private double autonomia;
    private Transporte transporteAlocado;  // Atributo para armazenar o transporte alocado
    private boolean alocado;

    public Drone(int codigo, double custoFixo, double autonomia) {
        setCodigo(codigo);
        setCustoFixo(custoFixo);
        setAutonomia(autonomia);
        this.alocado = false;
        this.transporteAlocado = null; // Inicializa sem transporte alocado
    }

    public boolean isAlocado() {
        return alocado;
    }

    public void setAlocado(boolean alocado) {
        this.alocado = alocado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getCustoFixo() {
        return custoFixo;
    }

    public void setCustoFixo(double custoFixo) {
        this.custoFixo = custoFixo;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public abstract double calculaCustoKm();

    public abstract boolean isDisponivel(Transporte transporte);

    // Método para obter o transporte alocado
    public Transporte getTransporte() {
        return transporteAlocado;
    }

    // Método para associar um transporte ao drone (quando alocado)
    public void setTransporte(Transporte transporte) {
        this.transporteAlocado = transporte;
    }

    @Override
    public String toString() {
        return String.format("Codigo: %d\nCusto Fixo: %.2f\nAutonomia: %.2f", getCodigo(),
                getCustoFixo(),
                getAutonomia());
    }

    public String geraCSV() {
        return String.format("%d;%.2f;%.2f", getCodigo(), getCustoFixo(), getAutonomia());
    }
}
