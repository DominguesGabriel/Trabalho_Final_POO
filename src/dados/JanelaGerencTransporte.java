package dados;

import javax.swing.*;

public class JanelaGerencTransporte extends JDialog {
    private TelaGerenciamentoTransporte tgt;

    public JanelaGerencTransporte (Administracao adm) {
        super();
        tgt = new TelaGerenciamentoTransporte(adm, JanelaGerencTransporte.this);
        this.add(tgt.getPainel());
        setSize(850, 500);
        setTitle("Dados E/S");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setModal(true);
        setVisible(true);
    }
}
