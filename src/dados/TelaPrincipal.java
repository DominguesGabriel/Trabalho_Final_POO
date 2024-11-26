package dados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPrincipal {
    private Administracao adm;
    private Drones drones;
    private JButton cadastrarTransporteButton;
    private JButton cadastrarDronePessoalButton;
    private JButton botaoDC;
    private JButton botaoDadosES;
    private JButton relatorioGeralButton;
    private JButton mostrarTransportesButton;
    private JButton gerenciarTransportesButton;
    private JButton sairButton;
    private JTextArea textArea1;
    private JPanel painel;

    public TelaPrincipal(Drones drones, Administracao adm) {
        this.drones = drones;
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
                ArrayList<Transporte> transportes = adm.getLista();
                StringBuilder sb = new StringBuilder();

                if (transportes.isEmpty()) {
                    sb.append("Nenhum transporte cadastrado.");
                } else {
                    sb.append("Transportes cadastrados:\n");
                    for (Transporte transporte : transportes) {
                        sb.append(transporte.toString()).append("\n");

                        // Verifica se o transporte está alocado a um drone
                        if (transporte.getSituacao().equals(Estado.ALOCADO)) {
                            Drone droneAlocado = transporte.getDroneAlocado(); // Supondo que exista um método getDroneAlocado()
                            if (droneAlocado != null) {
                                sb.append("Drone alocado: ").append(droneAlocado.toString()).append("\n");
                                sb.append("Custo final do transporte: ").append(transporte.calculaCusto()).append("\n");
                            } else {
                                sb.append("Drone alocado não encontrado.\n");
                            }
                        }
                        sb.append("\n"); // Linha em branco entre transportes
                    }
                }

                textArea1.setText(sb.toString()); // Define o texto da JTextArea
            }
        });

        relatorioGeralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Transporte> transportes = adm.getLista();
                ArrayList<Drone> drones1 = drones.getListaDrones();
                StringBuilder sb = new StringBuilder();

                if (drones1.isEmpty() && transportes.isEmpty()) {
                    // Caso ambas as listas estejam vazias
                    sb.append("Nenhum drone e nenhum transporte cadastrado.");
                } else {
                    // Caso existam elementos
                    if (!drones1.isEmpty()) {
                        sb.append("Drones cadastrados:\n");
                        for (Drone drone : drones1) {
                            sb.append(drone.toString()).append("\n");
                        }
                    } else {
                        sb.append("Nenhum drone cadastrado.\n");
                    }

                    if (!transportes.isEmpty()) {
                        sb.append("\nTransportes cadastrados:\n");
                        for (Transporte transporte : transportes) {
                            sb.append(transporte.toString()).append("\n");
                        }
                    } else {
                        sb.append("\nNenhum transporte cadastrado.");
                    }
                }

                // Atualiza o texto da JTextArea
                textArea1.setText(sb.toString());
            }
        });

    }

        public JPanel getPainel() {
            return painel;
        }
}
