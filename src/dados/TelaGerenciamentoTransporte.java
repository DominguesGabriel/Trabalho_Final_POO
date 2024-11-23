package dados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerenciamentoTransporte {
    private Administracao adm;
    private JPanel panel1;
    private JButton processarButton;
    private JTextField textField1;
    private JButton alterarSituacaoButton;
    private JButton voltarButton;
    private JTextArea areaTexto;
    private JComboBox selecionaEstadoTransporte;

    public TelaGerenciamentoTransporte() {

        alterarSituacaoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                int numero = Integer.parseInt(textField1.getText());
                Transporte t = buscarTransportePorCodigo(numero);
                if(t == null){
                    areaTexto.setText("Transporte não encontrado!");
                }
                areaTexto.setText("Dados do Transporte:\n" +
                        "Código: " + t.getNumero() + "\n" +
                        "Situação Atual: " + t.getSituacao());

                if(t.getSituacao().equals(Estado.TERMINADO) || t.getSituacao().equals(Estado.CANCELADO)){
                    areaTexto.setText("Não é possível alterar um transporte TERMINADO ou CANCELADO!");
                    return;
                }
                switch (selecionaEstadoTransporte.getSelectedItem().toString()) {
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

                areaTexto.setText("Situação do transporte alterada com sucesso! Nova situação: " + t.getSituacao() );
            }catch(NumberFormatException ex){
                    areaTexto.setText("Número inválido! Digite um código numérico.");
                }
            }
        });
    }
    public Transporte buscarTransportePorCodigo(int numero){
        for(Transporte t : adm.getLista()) {
            if(t.getNumero() == numero){
                return t;
            }
        }
        return null;
    }
}
