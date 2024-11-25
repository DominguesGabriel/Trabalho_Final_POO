package dados;

import javax.swing.*;

public class JanelaCadastroDC extends JDialog{
    private DroneCargaCadastro dc;

    public JanelaCadastroDC(Drones base) {
        super();
        dc = new DroneCargaCadastro(base, JanelaCadastroDC.this);
        this.add(dc.getPainel());
        setSize(850, 500);
        setTitle("Cadastro de Drones de Carga");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setModal(true);
        setVisible(true);
    }
}
