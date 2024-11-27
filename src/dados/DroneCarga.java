package dados;

public abstract class DroneCarga extends Drone {
    private double pesoMaximo;

    public DroneCarga(int codigo, double custoFixo, double autonomia, double pesoMaximo) {
        super(codigo, custoFixo, autonomia);
        this.pesoMaximo = pesoMaximo;
    }
    @Override
    public boolean isDisponivel(Transporte transporte) {
        // Verifica se o drone está livre e se suporta o peso do transporte
        return !isAlocado() && transporte.getPeso() <= pesoMaximo;
    }

    @Override
    public String toString() {
        return String.format("%s\nPeso máximo: %.2f",
                super.toString(),
                getPesoMaximo());
    }



    public String geraCSV(){
        return String.format("%s;%.2f",super.geraCSV(),getPesoMaximo());
    }

    public double getPesoMaximo() {
        return pesoMaximo;
    }
}