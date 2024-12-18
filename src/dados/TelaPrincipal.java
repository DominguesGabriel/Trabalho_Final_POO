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

                if (adm.getLista().isEmpty()) {
                    sb.append("Nenhum transporte cadastrado.\n");
                } else {
                    for (Transporte t : adm.getLista()) {
                        sb.append("Transporte ").append(t.getNumero()).append(" - ").append(t.getDescricao()).append("\n")
                                .append("Peso: ").append(t.getPeso()).append(" kg\n")
                                .append("Origem: (").append(t.getLatitudeOrigem()).append(", ").append(t.getLongitudeOrigem()).append(")\n")
                                .append("Destino: (").append(t.getLatitudeDestino()).append(", ").append(t.getLongitudeDestino()).append(")\n")
                                .append("Situação: ").append(t.getSituacao()).append("\n");

                        if (t.getSituacao().equals(Estado.ALOCADO)) {
                            Drone droneAlocado = getDroneByTransporte(t);
                            if(droneAlocado != null) {
                                for (Drone d : drones.getListaDrones()) {
                                    if (d.isDisponivel(t)) {
                                        t.adcDrone(d);
                                        sb.append("Drone alocado: ").append(d.getCodigo()).append("\n")
                                                .append("Custo final do transporte: ").append(t.calculaCusto()).append("\n");
                                    }
                                }
                            }

                        }
                        sb.append("Drone alocado não encontrado.\n");

                        sb.append("-------------------------------------------------\n");
                        algumProcessado = true;
                    }
                }

                if (!algumProcessado) {
                    sb.append("Nenhum transporte processado.\n");
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
                    sb.append("Nenhum drone e nenhum transporte cadastrado.");
                } else {
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
    private Drone getDroneByTransporte(Transporte t) {
        for (Drone d : drones.getListaDrones()) {
            if (d.isAlocado() && d.getTransporte() == t) {
                return d;
            }
        }
        return null;
    }

        public JPanel getPainel() {
            return painel;
        }
}
