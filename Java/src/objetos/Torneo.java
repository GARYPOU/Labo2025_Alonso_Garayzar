package objetos;
import personas.Jugador;

import java.util.ArrayList;
import java.util.Objects;

public class Torneo {
    private ArrayList<Equipo>equipos;
    private ArrayList<Partido>partidos;
    private ArrayList<Equipo>EquiposManana;
    private ArrayList<Equipo>EquiposTarde;
    private ArrayList<Equipo>EquiposNoche;
    public Torneo(){
    if(equipos.size()<20) {
        this.equipos = new ArrayList<>();
    }
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public ArrayList<Equipo> getEquiposManana() {
        return EquiposManana;
    }

    public void setEquiposManana(ArrayList<Equipo> equiposManana) {
        EquiposManana = equiposManana;
    }

    public ArrayList<Equipo> getEquiposTarde() {
        return EquiposTarde;
    }

    public void setEquiposTarde(ArrayList<Equipo> equiposTarde) {
        EquiposTarde = equiposTarde;
    }

    public ArrayList<Equipo> getEquiposNoche() {
        return EquiposNoche;
    }

    public void setEquiposNoche(ArrayList<Equipo> equiposNoche) {
        EquiposNoche = equiposNoche;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Torneo(ArrayList<Equipo>equipos) {
    this.equipos = equipos;


}
public void equiposMañana(String hora) {
    for (int i = 0; i < equipos.size(); i++) {
        if (equipos.get(i).getHorario() == hora) {
            EquiposManana.add(getEquipos().get(i));
        }

    }
}
    public void equiposTarde(String hora) {
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getHorario() == hora) {
                EquiposTarde.add(getEquipos().get(i));
            }

        }
    }
        public void equiposNoche(String hora) {
            for (int i = 0; i < equipos.size(); i++) {
                if (equipos.get(i).getHorario() == hora) {
                    EquiposNoche.add(getEquipos().get(i));
                }

            }
        }


public ArrayList<Partido> fixture(ArrayList<Equipo>equipos) {
    String VS;
    ArrayList<Partido>partidos = new ArrayList<>();
    for (int i = 0; i < equipos.size(); i++) {
        for (int j = 1; j <= equipos.size(); i++) {
            VS = equipos.get(i).getNombre() + "VS" + equipos.get(j + i).getNombre();
            partidos.add(new Partido(1, 2, 2000, equipos.get(i).getHorario(), VS));

        }
    }
    return partidos;
}


public static void main(String[] args) {
        Torneo t1 = new Torneo();
        t1.equiposMañana("mañana");
        t1.equiposTarde("tarde");
        t1.equiposNoche("noche");
        t1.fixture(t1.EquiposManana);
        t1.fixture(t1.EquiposTarde);
        t1.fixture(t1.EquiposNoche);
    }
}