package dados;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {
    private TelaPrincipal tp;

    public JanelaPrincipal(Drones drones, Administracao adm) {
        super();
        tp = new TelaPrincipal(drones, adm);
        this.add(tp.getPainel());
        setSize(850, 500);
        setTitle("ACMEAirDrones");
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        setVisible(true);
    }
}
