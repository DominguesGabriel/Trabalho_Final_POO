package dados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDronePessoal {
    private JTextField codigoText;
    private JTextArea mensagemArea;
    private JButton cadastrarButton;
    private JTextField custoText;
    private JTextField autonomiaText;
    private JTextField quantidadeText;
    private JButton limparButton;
    private JButton mostrarDronesButton;
    private JButton voltarButton;
    private JPanel painel;

    public TelaDronePessoal(Drones drones, JanelaCadastroDP janela) {
        mensagemArea.setEditable(false);
        mensagemArea.setLineWrap(true);        // Habilita a quebra de linha automática
        mensagemArea.setWrapStyleWord(true);   // Faz com que a quebra seja entre palavras
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo;
                double custo;
                double autonomia;
                int quantidade;
                String[] dados = new String[4];
                dados[0] = codigoText.getText().trim();
                dados[1] = custoText.getText().trim();
                dados[2] = autonomiaText.getText().trim();
                dados[3] = quantidadeText.getText().trim();

                for (int i = 0; i < dados.length; i++) {
                    if (dados[i].isEmpty()) {
//                        mensagemArea.setForeground(Color.RED);
                        mensagemArea.setText("ERRO: Todos os campos devem ser preenchidos.");
                        return;
                    }

                }

                try {
                    codigo = Integer.parseInt(dados[0]);
                    custo = Double.parseDouble(dados[1]);
                    autonomia = Double.parseDouble(dados[2]);
                    quantidade = Integer.parseInt(dados[3]);
                } catch (NumberFormatException ex) {
//                    mensagemArea.setForeground(Color.RED);
                    mensagemArea.setText("ERRO: Verifique os valores inseridos. Certifique-se de que o código e a quantidade são números inteiros, e o custo e a autonomia são números decimais.");
                    return;
                }

                double teste;
                for (int i = 0; i < dados.length; i++) {
                    teste = Double.parseDouble(dados[i]);
                    if (teste <= 0) {
//                        mensagemArea.setForeground(Color.RED);
                        mensagemArea.setText("ERRO:Impossível cadastrar valores negativos nos campos solicitados.");
                        return;
                    }
                }

                Drone drone = new DronePessoal(codigo, custo, autonomia, quantidade);
                if(drones.addDrone(drone)){
//                    mensagemArea.setForeground(Color.GREEN);
                    mensagemArea.setText("Drone cadastrado com sucesso!");
                }else{
//                    mensagemArea.setForeground(Color.RED);
                    mensagemArea.setText("Falha ao cadastrar drone, o código deve ser único.");
                }
                limparCampos();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
                mensagemArea.setText("");
            }
        });
        mostrarDronesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drones.estaVazia()){
//                    mensagemArea.setForeground(Color.RED);
                    mensagemArea.setText("Não há drones cadastrados.");
                    return;
                }
//                mensagemArea.setForeground(Color.BLACK);
                mensagemArea.setText(drones.imprimirDrones());
            }
        });
    }
    public JPanel getPainel() {
        return painel;
    }

    private void limparCampos() {
        codigoText.setText("");
        custoText.setText("");
        autonomiaText.setText("");
        quantidadeText.setText("");
    }

}
