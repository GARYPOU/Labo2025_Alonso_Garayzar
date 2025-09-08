package objetos.Eleccion;

import java.util.HashSet;

public class Sistema {
    HashSet<Partido_politico>partidoPoliticos;

    public Sistema(HashSet<Partido_politico> partidoPoliticos) {
        this.partidoPoliticos = partidoPoliticos;
    }

    public HashSet<Partido_politico> getPartidoPoliticos() {
        return partidoPoliticos;
    }

    public void setPartidoPoliticos(HashSet<Partido_politico> partidoPoliticos) {
        this.partidoPoliticos = partidoPoliticos;
    }

}
