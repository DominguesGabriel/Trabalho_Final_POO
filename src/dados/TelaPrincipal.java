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
                StringBuilder sb = new StringBuilder();
                boolean algumProcessado = false;

                for (Transporte t : adm.getLista()) {
                    if (t.getSituacao().equals(Estado.PENDENTE)) {
                        boolean alocado = false;

                        for (Drone d : drones.getListaDrones()) {
                            if (d.isDisponivel(t)) { // Verifica se o drone está disponível
                                d.setAlocado(true); // Marca o drone como alocado
                                t.setSituacao(Estado.ALOCADO); // Atualiza o estado do transporte
                                sb.append("Transporte ").append(t.getNumero()).append(" alocado ao drone ").append(d.getCodigo()).append("\n");
                                alocado = true;
                                algumProcessado = true;
                                break; // Sai do loop de drones
                            }
                        }

                        if (!alocado) {
                            sb.append("Transporte ").append(t.getNumero()).append(" não pôde ser alocado.\n");
                        }
                    }
                }

                if (!algumProcessado) {
                    sb.append("Nenhum transporte pendente para processar.\n");
                }

                textArea1.setText(sb.toString());
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
