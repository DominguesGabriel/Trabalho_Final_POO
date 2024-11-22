package dados;

import javax.swing.*;

public class JanelaCadastroDC extends JFrame {
    private DroneCargaCadastro dc;

    public JanelaCadastroDC(Drones base) {
        super();
        dc = new DroneCargaCadastro(base);
        this.add(dc.getPainel());
        setSize(850, 500);
        setTitle("Cadastro de Drones de Carga");
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
        setVisible(true);
    }
}
