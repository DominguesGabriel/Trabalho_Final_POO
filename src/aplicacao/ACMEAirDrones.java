package aplicacao;
import dados.Drone;
import dados.DronePessoal;
import dados.Drones;
import dados.JanelaCadastroDP;
import dados.DroneCargaInanimada;
import dados.DroneCargaViva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class ACMEAirDrones {
    private Drones drones;
    private PrintWriter writer;

    public ACMEAirDrones() {
        drones = new Drones();
    }
    public void executar(){
        new JanelaCadastroDP(drones);
    }
}




