package dados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class telaTransporte {
    private JPanel painelTransporte;
    private JButton CADASTRARButton;
    private JTextArea campoMensagem;
    private JButton LIMPARButton;
    private JButton DADOSButton;
    private JButton voltarButton;
    private JComboBox tipoTransporte;
    private JTextField textFieldPeso;
    private JTextField textFieldNumero;
    private JTextField textFieldCliente;
    private JTextField textFieldDesc;
    private JTextField textFieldLatOr;
    private JTextField textFieldLatDest;
    private JTextField textFieldLongOr;
    private JTextField textFieldLongDest;
    private JTextField textFieldQtdPessoas;
    private JTextField textFieldTempMin;
    private JTextField textFieldTempMax;
    private JLabel txtTempMinima;
    private JLabel txtTempMaxima;
    private JLabel txtQtdPessoas;
    private JCheckBox trueCheckBox;
    private JCheckBox falseCheckBox;


    public telaTransporte (Administracao adm, JanelaTransporte janela) {

        CADASTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transporte transporte =null;
                TransporteCargaInanimada transporteCarga;

                try{
                            int peso = Integer.parseInt(textFieldPeso.getText());
                            int numero = Integer.parseInt(textFieldNumero.getText());
                            String nomeCliente = textFieldCliente.getText();
                            String descricao = textFieldDesc.getText();
                            double latOrigem = Double.parseDouble(textFieldLatOr.getText());
                            double latDestino = Double.parseDouble(textFieldLatDest.getText());
                            double longOrigem = Double.parseDouble(textFieldLongOr.getText());
                            double longDestino = Double.parseDouble(textFieldLongDest.getText());
                            if (tipoTransporte.getSelectedItem().toString().equals("Pessoal")) {
                                int qtdPessoas = Integer.parseInt(textFieldQtdPessoas.getText());
                                transporte = new TransportePessoal(numero, nomeCliente, descricao, peso, latOrigem, latDestino, longOrigem, longDestino, qtdPessoas);
                            } else if (tipoTransporte.getSelectedItem().toString().equals("Carga Viva")) {
                                double tempMaxima = Double.parseDouble(textFieldTempMax.getText());
                                double tempMinima = Double.parseDouble(textFieldTempMin.getText());
                                transporte = new TransporteCargaViva(numero, nomeCliente, descricao, peso, latOrigem, latDestino, longOrigem, longDestino, tempMinima, tempMaxima);

                            } else if (tipoTransporte.getSelectedItem().toString().equals("Carga Inanimada")) {
                                if (trueCheckBox.isSelected()) {
                                    transporte = new TransporteCargaInanimada(numero, nomeCliente, descricao, peso, latOrigem, latDestino, longOrigem, longDestino, true);
                                } else {
                                    transporte = new TransporteCargaInanimada(numero, nomeCliente, descricao, peso, latOrigem, latDestino, longOrigem, longDestino, false);
                                }
                            }

                    if (!adm.addTransporte(transporte)) {
                        campoMensagem.setText("Erro: Já existe um transporte com o código informado!");
                        return;
                    }

                    campoMensagem.setText("Cadastro realizado com sucesso!!");


                }catch(NumberFormatException ex){
                    campoMensagem.setText("Erro ao cadastrar os dados: certifique-se que todos os campos do seu tipo de transporte foram preenchidos corretamente!");
                }catch(Exception ex){
                    campoMensagem.setText("Erro: certifique-se de selecionar o tipo de carga que desejas cadastrar!" );
                }
                if (transporte.getNumero() <= 0 ) {
                    campoMensagem.setText("Erro: Número de transporte inválido!");
                }
                if(transporte.getPeso() <=0){
                    campoMensagem.setText("Erro: Peso da carga inválido!");
                }
                if (tipoTransporte.getSelectedItem().toString().equals("Pessoal")) {
                    if (textFieldQtdPessoas.getText() == null || textFieldQtdPessoas.getText().equals("")) {
                        campoMensagem.setText("Erro: O campo 'Quantidade de Pessoas' precisa ser preenchido!");
                        return;
                    }
                }else if(tipoTransporte.getSelectedItem().toString().equals("Carga Viva")){
                    if (textFieldTempMin.equals("") || textFieldTempMax.equals("")) {
                        campoMensagem.setText("Erro: Os campos de temperatura mínima e máxima devem ser preenchidos!");
                        return;
                    }
                }else if(tipoTransporte.getSelectedItem().toString().equals("Carga Inanimada")){}
                if(trueCheckBox.isSelected() && falseCheckBox.isSelected()){
                    campoMensagem.setText("Erro: Apenas uma opção pode ser selecionada para a Carga Inanimada");
                    return;
                }

            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
            }
        });
        LIMPARButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                textFieldPeso.setText("");
                textFieldNumero.setText("");
                textFieldCliente.setText("");
                textFieldDesc.setText("");
                textFieldLatOr.setText("");
                textFieldLatDest.setText("");
                textFieldLongOr.setText("");
                textFieldLongDest.setText("");
                textFieldQtdPessoas.setText("");
                textFieldTempMax.setText("");
                textFieldTempMin.setText("");

            }
        });
        DADOSButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e){
               StringBuilder dados = new StringBuilder();
               for(Transporte transporte : adm.getLista()){
                   dados.append(transporte).append("\n");
               }
               campoMensagem.setText(dados.toString());
           }
        });

    }
    public JPanel getPainelTransporte() {
        return painelTransporte;
    }
}
