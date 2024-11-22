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
        return String.format("dados.Drone Pessoal\n%s\nQuantida máxima de pessoas: %d\n\n",
                super.toString(),
                getQuantidaMaximaPessoas());
    }

    public String geraCSV(){
        return String.format("1;%s;%d",super.geraCSV(),getQuantidaMaximaPessoas());
    }
}

