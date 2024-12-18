package dados;

import java.util.ArrayList;

public class Administracao {

private ArrayList<Transporte> lista;

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

}
