package dados;

import java.util.ArrayList;

public class Administracao {

private ArrayList<Transporte> lista;
private Transporte transporte;

public Administracao() {
    lista = new ArrayList<>();
}
public boolean addTransporte(Transporte t) {
    boolean resp = true;
    for(Transporte transp : lista) {
        if(transp.getNumero() == t.getNumero()) {
            resp = false;
            return resp;
        }
    }

    lista.add(t);
    t.setSituacao(Estado.PENDENTE);
    return resp;
}
public ArrayList<Transporte> getLista() {
    return lista;
}

public double calculaCustoVariado(){
    // o tipo de transporte vai ser verificado na tela do transporte com o if fora do try/catch

    return 0;

}

}
