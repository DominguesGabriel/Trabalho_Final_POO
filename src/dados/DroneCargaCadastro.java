package dados;

import javax.swing.*;
import java.awt.event.*;

public class DroneCargaCadastro {
    private JTextField codigo;
    private JTextArea areaTexto;
    private JButton cadastrar;
    private JTextField custoFixo;
    private JRadioButton cargaViva;
    private JTextField autonomia;
    private JTextField pesoMax;
    private JRadioButton climatizado;
    private JRadioButton cargaInanimada;
    private JRadioButton protecao;
    private JButton dados;
    private JButton limpar;
    private JButton sair;
    private JPanel painel;
    private JScrollPane scroll;

    public DroneCargaCadastro(Drones base) {

        ButtonGroup tipoGrupo = new ButtonGroup();
        tipoGrupo.add(cargaViva);
        tipoGrupo.add(cargaInanimada);

        areaTexto.setEditable(false);

        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (codigo.getText().trim().isEmpty() || custoFixo.getText().trim().isEmpty() || autonomia.getText().trim().isEmpty() || pesoMax.getText().trim().isEmpty() || (!cargaViva.isSelected() && !cargaInanimada.isSelected())) {
                    areaTexto.setText("ERRO: Todos os campos devem ser preenchidos e um tipo de carga selecionado.");
                    return;
                }

                int codigo1 = 0;
                double custoF1 = 0, autonomia1 = 0, pesoMax1 = 0;

                try {
                    codigo1 = Integer.parseInt(codigo.getText().trim());
                    custoF1 = Double.parseDouble(custoFixo.getText().trim());
                    autonomia1 = Double.parseDouble(autonomia.getText().trim());
                    pesoMax1 = Double.parseDouble(pesoMax.getText().trim());
                }
                catch (NumberFormatException ex) {
                    areaTexto.setText("ERRO: Verifique se todos os campos numéricos estão preenchidos corretamente.");
                    return;
                }

                if (cargaViva.isSelected() && protecao.isSelected()) {
                    areaTexto.setText("ERRO: Cargas vivas não possuem extra de proteção.\nApenas selecione (ou não) o extra climatizado");
                    return;
                }
                else if (cargaInanimada.isSelected() && climatizado.isSelected()) {
                    areaTexto.setText("ERRO: Cargas inanimadas não possuem extra de climatização.\nApenas selecione (ou não) o extra proteção");
                    return;
                }
                if (codigo1 < 0 || custoF1 < 0 || autonomia1 < 0 || pesoMax1 < 0){
                    areaTexto.setText("ERRO: Todos os campos devem ser preenchidos com valores positivos");
                    return;
                }

                if (cargaViva.isSelected()) {
                    boolean climatizado1 = climatizado.isSelected();
                    DroneCargaViva d = new DroneCargaViva(codigo1, custoF1, autonomia1, pesoMax1, climatizado1);

                    if (base.addDrone(d)) {
                        areaTexto.setText("Drone cadastrado com sucesso!");
                        limpador();
                    }
                    else {
                        areaTexto.setText("ERRO: Drone não cadastrado - código já existente.");
                    }
                }
                else if (cargaInanimada.isSelected()) {
                    boolean protecao1 = protecao.isSelected();
                    DroneCargaInanimada d = new DroneCargaInanimada(codigo1, custoF1, autonomia1, pesoMax1, protecao1);

                    if (base.addDrone(d)) {
                        areaTexto.setText("Drone cadastrado com sucesso!");
                        limpador();
                    }
                    else {
                        areaTexto.setText("ERRO: Drone não cadastrado - código já existente.");
                    }
                }
            }
        });

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpador();
            }
        });
        dados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                areaTexto.setText("");
//                if (base.estaVazia()){
//                    areaTexto.setText("AVISO! Nenhum drone de carga cadastrado.");
//                }
//                for (Drone d : base.getBaseDrones()){
//                        if (d instanceof DroneCargaInanimada){
//                            areaTexto.append("Drone de Carga Inanimada\n"+d.toString()+"\n\n");
//                        }
//                        else if (d instanceof DroneCargaViva){
//                            areaTexto.append("Drone de Carga Viva\n" + d.toString()+"\n\n");
//                        }
//                }
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }

    private void limpador(){
        codigo.setText("");
        custoFixo.setText("");
        autonomia.setText("");
        pesoMax.setText("");
        climatizado.setSelected(false);
        protecao.setSelected(false);
    }
}
