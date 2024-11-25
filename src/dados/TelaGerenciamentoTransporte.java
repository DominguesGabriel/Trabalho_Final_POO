package dados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerenciamentoTransporte {
    private Administracao adm; // Variável de instância para armazenar a administração
    private JPanel painel;
    private JButton processarButton;
    private JTextField textField1;
    private JButton alterarSituacaoButton;
    private JButton voltarButton;
    private JTextArea areaTexto;
    private JComboBox selecionaEstadoTransporte;


    public TelaGerenciamentoTransporte(Administracao adm, JanelaGerencTransporte janela) {
        this.adm = adm;

        alterarSituacaoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(textField1.getText());
                    Transporte t = buscarTransportePorCodigo(numero);

                    if (t == null) {
                        areaTexto.setText("Transporte não encontrado!");
                        return;
                    }


                    areaTexto.setText("Dados do Transporte:\n" +
                            "Código: " + t.getNumero() + "\n" +
                            "Situação Atual: " + t.getSituacao());


                    if (t.getSituacao().equals(Estado.TERMINADO) || t.getSituacao().equals(Estado.CANCELADO)) {
                        areaTexto.setText("Não é possível alterar um transporte TERMINADO ou CANCELADO!");
                        return;
                    }

                    switch (selecionaEstadoTransporte.getSelectedItem().toString().trim()) {
                        case "Pendente":
                            t.setSituacao(Estado.PENDENTE);
                            break;
                        case "Alocado":
                            t.setSituacao(Estado.ALOCADO);
                            break;
                        case "Terminado":
                            t.setSituacao(Estado.TERMINADO);
                            break;
                        case "Cancelado":
                            t.setSituacao(Estado.CANCELADO);
                            break;
                        default:
                            areaTexto.append("\nEstado inválido!");
                            return;
                    }

                    areaTexto.setText("Situação do transporte alterada com sucesso! Nova situação: " + t.getSituacao());
                } catch (NumberFormatException ex) {
                    areaTexto.setText("Número inválido! Digite um código numérico.");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false); // Fecha a janela ao clicar no botão "Voltar"
            }
        });


    }


    public Transporte buscarTransportePorCodigo(int numero) {
        if (adm == null) { // Verifica se a administração foi inicializada
            throw new IllegalStateException("A instância de Administracao não foi inicializada.");
        }


        for (Transporte t : adm.getLista()) {
            if (t.getNumero() == numero) {
                return t; // Retorna o transporte encontrado
            }
        }
        return null;
    }

    public JPanel getPainel() {
        return painel;
    }
}
