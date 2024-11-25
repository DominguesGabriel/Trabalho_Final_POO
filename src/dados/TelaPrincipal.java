package dados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal {
    private JButton cadastrarTransporteButton;
    private JButton cadastrarDronePessoalButton;
    private JButton botaoDC;
    private JButton botaoDadosES;
    private JButton relatórioGeralButton;
    private JButton mostrarTransportesButton;
    private JButton gerenciarTransportesButton;
    private JButton sairButton;
    private JTextArea textArea1;
    private JPanel painel;

    public TelaPrincipal(Drones drones, Administracao adm) {

        botaoDC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelaCadastroDC(drones);
            }
        });
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        botaoDadosES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelaDadosES(drones, adm);
            }
        });
        cadastrarDronePessoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelaCadastroDP(drones);
            }
        });
        cadastrarTransporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelaTransporte(adm);
            }
        });
        gerenciarTransportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelaGerencTransporte(adm);
            }
        });
    }

        public JPanel getPainel() {
            return painel;
        }
}
