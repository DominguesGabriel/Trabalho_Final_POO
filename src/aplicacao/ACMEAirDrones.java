package aplicacao;
import dados.*;

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
    private Administracao adm;
    private PrintWriter writer;

    public void executar(){
        drones = new Drones();
        adm = new Administracao();
        new JanelaPrincipal(drones, adm);
    }
}




