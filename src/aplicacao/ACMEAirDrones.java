package aplicacao;
import dados.Drones;
import dados.JanelaCadastroDP;
public class ACMEAirDrones {
    private Drones drones;

    public ACMEAirDrones() {
        drones = new Drones();
    }
    public void executar(){
        new JanelaCadastroDP(drones);
    }

}

