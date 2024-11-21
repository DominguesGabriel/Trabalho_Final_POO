package dados;

import javax.swing.*;

public class JanelaCadastroDP extends JFrame {
    private TelaDronePessoal telaDronePessoal;
    public JanelaCadastroDP(Drones drones) {
        super();
        telaDronePessoal = new TelaDronePessoal(drones);
        this.add(telaDronePessoal.getPainel());
        this.setSize(850,500);
        this.setTitle("Cadastro de dados.Drones Pessoais");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
