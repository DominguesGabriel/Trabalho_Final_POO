package dados;

import javax.swing.*;

public class JanelaTransporte extends JDialog {
    private telaTransporte tela;

    public JanelaTransporte(Administracao adm) {
        super();
        tela = new telaTransporte(adm, JanelaTransporte.this);
        this.add(tela.getPainelTransporte());

        setSize(800,600);
        setTitle("Cadastro de Transporte");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setModal(true);
        setVisible(true);
    }

}
