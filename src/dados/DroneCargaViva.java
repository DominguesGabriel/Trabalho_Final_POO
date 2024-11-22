package dados;

public class DroneCargaViva extends DroneCarga {
    private boolean climatizado;

    public DroneCargaViva(int codigo, double custoFixo, double autonomia, double pesoMaximo ,boolean climatizado) {
        super(codigo,custoFixo,autonomia,pesoMaximo);
        this.climatizado = climatizado;
    }

    @Override
    public double calculaCustoKm() {
        double variavel;
        if (climatizado) {
            variavel = 20.0;
        }
        else variavel = 10.0;
        return getCustoFixo() + variavel;
    }

    @Override
    public String toString() {
        return super.toString()+"\nClimatizado: " + climatizado + "\nCusto por Km (R$): " + calculaCustoKm();
    }
}