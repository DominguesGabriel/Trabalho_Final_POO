package dados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPrincipal {
    private Administracao adm;
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
        this.adm = adm;

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

        mostrarTransportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Transporte> transportes = adm.getLista(); // Obtém a lista de transportes
                StringBuilder sb = new StringBuilder();

                if (transportes.isEmpty()) {
                    sb.append("Nenhum transporte cadastrado.");
                } else {
                    sb.append("Transportes cadastrados:\n");
                    for (Transporte transporte : transportes) {
                        sb.append(transporte.toString()).append("\n"); // Supondo que cada Transporte tenha um `toString` bem formatado
                    }
                }

                textArea1.setText(sb.toString()); // Define o texto da JTextArea
            }
        });
    }

        public JPanel getPainel() {
            return painel;
        }
}
