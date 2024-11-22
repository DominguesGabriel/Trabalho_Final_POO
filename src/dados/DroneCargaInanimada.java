package dados;

public class DroneCargaInanimada extends DroneCarga {
    private boolean protecao;

    public DroneCargaInanimada(int codigo, double custoFixo, double autonomia, double pesoMaximo, boolean protecao) {
        super(codigo, custoFixo, autonomia, pesoMaximo);
        this.protecao = protecao;
    }

    @Override
    public double calculaCustoKm() {
        double variavel;
        if (protecao) {
            variavel = 10.0;
        }
        else variavel = 5.0;
        return getCustoFixo() + variavel;
    }

    @Override
    public String toString() {
        return super.toString()+"\nProteção: " + protecao +"\nCusto por Km (R$): " + calculaCustoKm();
    }
}
