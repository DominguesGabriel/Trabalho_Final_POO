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

    public TelaDadosES(Drones drones, Administracao transportes, JanelaDadosES janela) {
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);        // Habilita a quebra de linha automática
        areaTexto.setWrapStyleWord(true);   // Faz com que a quebra seja entre palavras
        Locale.setDefault(Locale.ENGLISH);
        this.drones = drones;
        this.transportes = transportes;

        salvarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeArquivo.getText().trim();
                if (nome.isEmpty()) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.append("Erro: Por favor, insira o nome do arquivo.\n");
                    return;
                }
                if (!nome.matches("[a-zA-Z0-9._-]+")) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.append("Erro: O nome do arquivo contém caracteres inválidos.\n");
                    return;
                }

                Path path = Paths.get(nome + ".csv");
                if (Files.exists(path)) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.append("Erro: O arquivo já existe. Escolha outro nome ou exclua o arquivo.\n");
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
                    areaTexto.append("Dados salvos com sucesso no arquivo: " + nome + ".csv\n");

                } catch (Exception exception) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.append("Erro ao salvar os dados: " + exception.getMessage() + "\n");
                }
                //leLinhasTexto();
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
                    areaTexto.append("Erro: Por favor, insira o nome do arquivo.\n");
                    return;
                }
                if (!nome.matches("[a-zA-Z0-9._-]+")) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.append("Erro: O nome do arquivo contém caracteres inválidos.\n");
                    return;
                }

                Path path = Paths.get(nome + ".csv");
                if (!Files.exists(path)) {
                    areaTexto.setForeground(Color.RED);
                    areaTexto.append("Erro: O arquivo '" + nome + ".csv' não foi encontrado.\n");
                    return;
                }

                leLinhasTexto(nome + ".csv");
            }
        });

    }
    private void fecharArquivo() {
        if (writer != null) {
            writer.close();
            writer = null; // Limpa a referência para evitar reutilização
        }
    }

    public void SalvarDrone(String line) {
        try {
            String[] dados = line.split(";");
            int tipo = Integer.parseInt(dados[0]);
            int codigo = Integer.parseInt(dados[1]);

            if (tipo == 1) {
                // Drone Pessoal
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                int quantidadeMaximaPessoas = Integer.parseInt(dados[4]);
                DronePessoal dronePessoal = new DronePessoal(codigo, custoFixo, autonomia, quantidadeMaximaPessoas);
                drones.addDrone(dronePessoal);
               // areaTexto.append("Drone Pessoal salvo: " + dronePessoal.geraCSV() + "\n");
            } else if (tipo == 2) {
                // Drone de Carga Inanimada
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                double pesoMaximo = Double.parseDouble(dados[4]);
                boolean protecao = Boolean.parseBoolean(dados[5]);
                DroneCargaInanimada droneCargaInanimada = new DroneCargaInanimada(codigo, custoFixo, autonomia, pesoMaximo, protecao);
                drones.addDrone(droneCargaInanimada);
                //areaTexto.append("Drone Carga Inanimada salvo: " + droneCargaInanimada.geraCSV() + "\n");
            } else if (tipo == 3) {
                // Drone de Carga Viva
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                double pesoMaximo = Double.parseDouble(dados[4]);
                boolean climatizado = Boolean.parseBoolean(dados[5]);
                DroneCargaViva droneCargaViva = new DroneCargaViva(codigo, custoFixo, autonomia, pesoMaximo, climatizado);
                drones.addDrone(droneCargaViva);
                //areaTexto.append("Drone Carga Viva salvo: " + droneCargaViva.geraCSV() + "\n");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            areaTexto.append("Erro: Dados insuficientes na linha '" + line + "'\n");
        } catch (NumberFormatException e) {
            areaTexto.append("Erro: Formato numérico inválido na linha '" + line + "'\n");
        } catch (Exception e) {
            areaTexto.append("Erro inesperado ao processar a linha '" + line + "': " + e.getMessage() + "\n");
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
                SalvarDrone(line);
            }
        } catch (Exception e) {
            System.err.printf("Erro de E/S ao ler o arquivo: %s%n", e.getMessage());
            areaTexto.setForeground(Color.RED);
            areaTexto.append("Erro ao carregar os dados do arquivo: " + e.getMessage() + "\n");
        }
    }

    public JPanel getPainel() {
        return painel;
    }

}
