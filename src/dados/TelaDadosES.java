package dados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.LinkedList;
import java.util.Queue;

public class TelaDadosES {
    private JTextArea areaTexto;
    private JButton leituraSimulaçãoButton;
    private JButton salvarDadosButton;
    private JButton carregarDadosButton;
    private JTextField nomeArquivo;
    private JButton voltar;
    private JPanel painel;
    private PrintWriter writer;
    private Drones drones;
    private Administracao transportes;
    private Queue<Transporte> filaTransportes;

    public TelaDadosES(Drones drones, Administracao transportes, JanelaDadosES janela) {
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);        // Habilita a quebra de linha automática
        areaTexto.setWrapStyleWord(true);   // Faz com que a quebra seja entre palavras
        Locale.setDefault(Locale.ENGLISH);
        this.drones = drones;
        this.transportes = transportes;
        filaTransportes = new LinkedList<Transporte>();

        salvarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeArquivo.getText().trim();
                if (nome.isEmpty()) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.setText("Erro: Por favor, insira o nome do arquivo.\n");
                    return;
                }
                if (!nome.matches("[a-zA-Z0-9._-]+")) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.setText("Erro: O nome do arquivo contém caracteres inválidos.\n");
                    return;
                }

                Path path = Paths.get(nome + ".csv");
                if (Files.exists(path)) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.setText("Erro: O arquivo já existe. Escolha outro nome ou exclua o arquivo.\n");
                    return;
                }

                try (BufferedWriter bw = Files.newBufferedWriter(path, Charset.defaultCharset());
                     PrintWriter writer = new PrintWriter(bw)) {  // Criado dentro do try-with-resources

                    writer.println("tipo;codigo;custofixo;autonomia;qtdmaxpessoas_pesomaximo;protecao_climatizado");
                    for (Drone drone : drones.getListaDrones()) {
                        writer.println(drone.geraCSV());
                    }
                    writer.println("tipo;numero;nomecliente;descricao;peso;latorigem;longorigem;latdestino;longdestino;qtdpessoas_perigosa_tempmin;tempmax");
                    for (Transporte t : transportes.getLista()) {
                        writer.println(t.geraCSV());
                    }

                    areaTexto.setForeground(Color.GREEN);
                    areaTexto.setText("Dados salvos com sucesso no arquivo: " + nome + ".csv\n");

                } catch (Exception exception) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.setText("Erro ao salvar os dados: " + exception.getMessage() + "\n");
                }
                fecharArquivo();
            }
        });
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.setVisible(false);
            }
        });
        carregarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeArquivo.getText().trim();
                if (nome.isEmpty()) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.setText("Erro: Por favor, insira o nome do arquivo.\n");
                    return;
                }
                if (!nome.matches("[a-zA-Z0-9._-]+")) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.setText("Erro: O nome do arquivo contém caracteres inválidos.\n");
                    return;
                }

                Path path = Paths.get(nome + ".csv");
                if (!Files.exists(path)) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.setText("Erro: O arquivo '" + nome + ".csv' não foi encontrado.\n");
                    return;
                }

                leLinhasTexto(nome + ".csv");
                areaTexto.setForeground(Color.GREEN);
                areaTexto.setText("Dados carregados com sucesso no arquivo: " + nome + ".csv\n");
            }
        });

        leituraSimulaçãoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void fecharArquivo() {
        if (writer != null) {
            writer.close();
            writer = null; // Limpa a referência para evitar reutilização
        }
    }

    public void SalvarDados(String line) {
        try {
            String[] dados = line.split(";");
            int tipo = Integer.parseInt(dados[0]);
            int codigo = Integer.parseInt(dados[1]);

            if (tipo == 1 && dados.length == 5) {
                // Drone Pessoal
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                int quantidadeMaximaPessoas = Integer.parseInt(dados[4]);
                DronePessoal dronePessoal = new DronePessoal(codigo, custoFixo, autonomia, quantidadeMaximaPessoas);
                drones.addDrone(dronePessoal);
            } else if (tipo == 2 && dados.length == 6) {
                // Drone de Carga Inanimada
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                double pesoMaximo = Double.parseDouble(dados[4]);
                boolean protecao = Boolean.parseBoolean(dados[5]);
                DroneCargaInanimada droneCargaInanimada = new DroneCargaInanimada(codigo, custoFixo, autonomia, pesoMaximo, protecao);
                drones.addDrone(droneCargaInanimada);
            } else if (tipo == 3 && dados.length == 6) {
                // Drone de Carga Viva
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                double pesoMaximo = Double.parseDouble(dados[4]);
                boolean climatizado = Boolean.parseBoolean(dados[5]);
                DroneCargaViva droneCargaViva = new DroneCargaViva(codigo, custoFixo, autonomia, pesoMaximo, climatizado);
                drones.addDrone(droneCargaViva);
            }
            else if (tipo == 1 && dados.length == 10) {
                //transporte pessoal
                String nomeCliente = dados[2];
                String descricao = dados[3];
                double peso = Double.parseDouble(dados[4]);
                double latitudeOrigem = Double.parseDouble(dados[5]);
                double latitudeDestino = Double.parseDouble(dados[6]);
                double longitudeOrigem = Double.parseDouble(dados[7]);
                double longitudeDestino = Double.parseDouble(dados[8]);
                int quantidadePessoas = Integer.parseInt(dados[9]);
                TransportePessoal transportePessoal = new TransportePessoal(codigo,nomeCliente,descricao,peso,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino,quantidadePessoas);
                transportes.addTransporte(transportePessoal);
            }else if(tipo == 2 && dados.length == 10) {
                //transporte carga inanimada
                String nomeCliente = dados[2];
                String descricao = dados[3];
                double peso = Double.parseDouble(dados[4]);
                double latitudeOrigem = Double.parseDouble(dados[5]);
                double latitudeDestino = Double.parseDouble(dados[6]);
                double longitudeOrigem = Double.parseDouble(dados[7]);
                double longitudeDestino = Double.parseDouble(dados[8]);
                boolean cargaPerigosa = Boolean.parseBoolean(dados[9]);
                TransporteCargaInanimada transporteCargaInanimada = new TransporteCargaInanimada(codigo,nomeCliente,descricao,peso,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino,cargaPerigosa);
                transportes.addTransporte(transporteCargaInanimada);
            }else if(tipo == 3 && dados.length == 11) {
                //transporte carga viva
                String nomeCliente = dados[2];
                String descricao = dados[3];
                double peso = Double.parseDouble(dados[4]);
                double latitudeOrigem = Double.parseDouble(dados[5]);
                double latitudeDestino = Double.parseDouble(dados[6]);
                double longitudeOrigem = Double.parseDouble(dados[7]);
                double longitudeDestino = Double.parseDouble(dados[8]);
                double temperaturaMinima = Double.parseDouble(dados[9]);
                double temperaturaMaxima = Double.parseDouble(dados[10]);
                TransporteCargaViva transporteCargaViva = new TransporteCargaViva(codigo,nomeCliente,descricao,peso,latitudeOrigem,latitudeDestino,longitudeOrigem,longitudeDestino,temperaturaMinima,temperaturaMaxima);
                transportes.addTransporte(transporteCargaViva);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            areaTexto.setText("Erro: Dados insuficientes na linha '" + line + "'\n");
        } catch (NumberFormatException e) {
            areaTexto.setText("Erro: Formato numérico inválido na linha '" + line + "'\n");
        } catch (Exception e) {
            areaTexto.setText("Erro inesperado ao processar a linha '" + line + "': " + e.getMessage() + "\n");
        }
    }


    public void leLinhasTexto(String nomeArquivo) {
        Path path = Paths.get(nomeArquivo);
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("tipo")) {
                    continue; // Ignora o cabeçalho
                }
                SalvarDados(line);
            }
        } catch (Exception e) {
            areaTexto.setForeground(Color.RED);
            areaTexto.setText("Erro ao carregar os dados do arquivo: " + e.getMessage() + "\n");
        }
    }

    public JPanel getPainel() {
        return painel;
    }

}
