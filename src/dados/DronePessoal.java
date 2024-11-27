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
        if (isAlocado()) {
            return false;
        }
        if (transporte instanceof TransportePessoal) {
            TransportePessoal transportePessoal = (TransportePessoal) transporte;
            return transportePessoal.getQtdPessoas() <= getQuantidaMaximaPessoas();
        }
        return false;
    }

    public String geraCSV(){
        return String.format("1;%s;%d",super.geraCSV(),getQuantidaMaximaPessoas());
    }
}

