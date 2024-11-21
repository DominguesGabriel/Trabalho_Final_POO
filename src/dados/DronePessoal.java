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
        return 0;
    }

    @Override
    public String toString() {
        return String.format("dados.Drone Pessoal\n%s\nQuantida máxima de pessoas: %d\n\n",
                super.toString(),
                getQuantidaMaximaPessoas());
    }}
