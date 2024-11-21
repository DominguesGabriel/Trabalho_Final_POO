package aplicacao;

import dados.Administracao;

import javax.swing.*;

public class JanelaTransporte extends JFrame {
    private telaTransporte tela;

    public JanelaTransporte(Administracao adm) {
        super();
        tela = new telaTransporte(adm);
        this.add(tela.getPainelTransporte());

        setSize(800,600);
        setTitle("Cadastro de Transporte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



    }

}
