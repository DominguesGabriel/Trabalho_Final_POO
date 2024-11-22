package dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Drones {
    private ArrayList<Drone> drones;

    public Drones() {
        drones = new ArrayList<Drone>();
    }

    public boolean addDrone(Drone d) {
        for(Drone drone : drones){
            if(drone.getCodigo() == d.getCodigo()){
                return false;
            }
        }
        drones.add(d);
        this.ordenar();
        return true;
    }

    private void ordenar() {
        Collections.sort(drones, new Comparator<Drone>() {
            public int compare(Drone d1, Drone d2) {
                return Integer.compare(d1.getCodigo(), d2.getCodigo());
            }
        });
    }

    public boolean estaVazia(){
        return drones.isEmpty();
    }

    public String imprimirDrones(){
        StringBuilder sb = new StringBuilder();

        if(drones.isEmpty()) {
            return "Não há drones cadastrados.";
        }

        for(Drone drone : drones){
            if(drone instanceof DronePessoal){
                sb.append(((DronePessoal)drone).toString()).append("\n");
            }
            else if(drone instanceof DroneCargaViva){
                sb.append(((DroneCargaViva)drone).toString()).append("\n");
            }
            else if (drone instanceof DroneCargaInanimada) {
                sb.append(((DroneCargaInanimada)drone).toString()).append("\n");
            }
        }
        return sb.toString();
    }

}
