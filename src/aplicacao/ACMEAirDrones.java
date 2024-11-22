package aplicacao;
import dados.Drone;
import dados.DronePessoal;
import dados.Drones;
import dados.JanelaCadastroDP;
import dados.DroneCargaInanimada;
import dados.DroneCargaViva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class ACMEAirDrones {
    private Drones drones;
    private PrintWriter writer;

    public ACMEAirDrones() {
        drones = new Drones();
        try {
            Path path = Paths.get("saida.csv");
            BufferedWriter bw = Files.newBufferedWriter(path, Charset.defaultCharset());
            writer = new PrintWriter(bw);
        } catch (Exception e) {
            writer.printf("Erro ao criar o arquivo: " + e.getMessage());
        }
    }
    public void executar(){
        Locale.setDefault(Locale.ENGLISH);
        leLinhasTexto();
        new JanelaCadastroDP(drones);
        fecharArquivo();
    }

    public void leLinhasTexto() {
        Path path = Paths.get("entrada.csv");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("tipo")) {
                    continue; // Ignora o cabeçalho
                }
                cadastrarDrone(line);
            }
        } catch (Exception e) {
            writer.printf("Erro de E/S: %s%n", e);
        }
    }

    public void cadastrarDrone(String line) {
        try {
            String[] dados = line.split(";");
            int tipo = Integer.parseInt(dados[0]);
            int codigo = Integer.parseInt(dados[1]);

            if (tipo == 1) {
                // Drone Pessoal
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                int quantidadeMaximaPessoas  = Integer.parseInt(dados[4]);
                DronePessoal dronePessoal = new DronePessoal(codigo, custoFixo, autonomia, quantidadeMaximaPessoas);
                drones.addDrone(dronePessoal);
                writer.println(dronePessoal.geraCSV());
            } else if (tipo == 2) {
                // Drone de Carga Inanimada
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                double pesoMaximo  = Double.parseDouble(dados[4]);
                boolean protecao = Boolean.parseBoolean(dados[5]);
                DroneCargaInanimada droneCargaInanimada = new DroneCargaInanimada(codigo, custoFixo, autonomia, pesoMaximo,protecao);
                drones.addDrone(droneCargaInanimada);
                writer.println(droneCargaInanimada.geraCSV());
            } else if (tipo == 3) {
                //Drone de Carva Viva
                double custoFixo = Double.parseDouble(dados[2]);
                double autonomia = Double.parseDouble(dados[3]);
                double pesoMaximo  = Double.parseDouble(dados[4]);
                boolean climatizado = Boolean.parseBoolean(dados[5]);
                DroneCargaViva droneCargaViva = new DroneCargaViva(codigo, custoFixo, autonomia, pesoMaximo,climatizado);
                drones.addDrone(droneCargaViva);
                writer.println(droneCargaViva.geraCSV());

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            writer.printf("Erro: Dados insuficientes na linha '%s'%n", line);
        } catch (NumberFormatException e) {
            writer.printf("Erro: Formato numérico inválido na linha '%s'%n", line);
        } catch (Exception e) {
            writer.printf("Erro inesperado ao processar a linha '%s': %s%n", line, e.getMessage());
        }
    }
    private void fecharArquivo() {
        writer.close();
    }

}




