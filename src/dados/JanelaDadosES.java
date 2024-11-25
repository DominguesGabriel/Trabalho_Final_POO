package dados;

import javax.swing.*;

public class JanelaDadosES extends JDialog {
    private TelaDadosES tes;

    public JanelaDadosES (Drones drones, Administracao adm) {
        super();
        tes = new TelaDadosES(drones, adm, JanelaDadosES.this);
        this.add(tes.getPainel());
        setSize(850, 500);
        setTitle("Dados E/S");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setModal(true);
        setVisible(true);
    }
}
