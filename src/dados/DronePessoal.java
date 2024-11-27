package dados;

public class DronePessoal extends Drone {

    private int quantidaMaximaPessoas;

    public DronePessoal(int codigo, double custoFixo, double autonomia,int quantidaMaximaPessoas) {
        super(codigo, custoFixo, autonomia);
        setQuantidaMaximaPessoas(quantidaMaximaPessoas);
    }

    public int getQuantidaMaximaPessoas() {
        return quantidaMaximaPessoas;
    }

    public void setQuantidaMaximaPessoas(int quantidaMaximaPessoas) {
        this.quantidaMaximaPessoas = quantidaMaximaPessoas;
    }

    @Override
    public double calculaCustoKm() {
        return getQuantidaMaximaPessoas() * 2;
    }

    @Override
    public String toString() {
        return String.format("Drone Pessoal\n%s\nQuantida máxima de pessoas: %d\n\n",
                super.toString(),
                getQuantidaMaximaPessoas());
    }
    @Override
    public boolean isDisponivel(Transporte transporte) {
        // Verifica se o drone não está alocado e se o transporte é compatível com o número de pessoas
        if (isAlocado()) {
            return false; // O drone já está alocado a outro transporte
        }

        // Verifica se o transporte é do tipo TransportePessoal
        if (transporte instanceof TransportePessoal) {
            TransportePessoal transportePessoal = (TransportePessoal) transporte;
            // Verifica se o drone suporta a quantidade de pessoas do transporte
            return transportePessoal.getQtdPessoas() <= getQuantidaMaximaPessoas();
        }

        // Se o transporte não for compatível (não é TransportePessoa), retorna falso
        return false;
    }


    public String geraCSV(){
        return String.format("1;%s;%d",super.geraCSV(),getQuantidaMaximaPessoas());
    }
}

