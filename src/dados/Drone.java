package dados;

public abstract class Drone {
    private int codigo;
    private double custoFixo;
    private double autonomia;

    public Drone(int codigo, double custoFixo, double autonomia) {
        setCodigo(codigo);
        setCustoFixo(custoFixo);
        setAutonomia(autonomia);
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

    @Override
    public String toString() {
        return  String.format("Codigo: %d\nCusto Fixo: %.2f\nAutonomia: %.2f",getCodigo(),
                getCustoFixo(),
                getAutonomia());
    }
    public String geraCSV(){
        return String.format("%d;%.2f;%.2f",getCodigo(),getCustoFixo(),getAutonomia());
    }
}
