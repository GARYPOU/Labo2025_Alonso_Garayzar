package objetos;

import personas.Jugador;

import java.util.ArrayList;
import java.util.Objects;

public class Torneo {
    private ArrayList<Equipo>equipos;
    private ArrayList<Partido>partidos;

public Torneo(){
    if(equipos.size()<20) {
        this.equipos = new ArrayList<>();
    }
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
public ArrayList<Partido> fixture(){
    String VS;
    for(int i=0; i<equipos.size(); i++){
        for (int j=1; j<=equipos.size();i++){
            if(Objects.equals(equipos.get(i).getHorario(), equipos.get(j + i).getHorario())){
                VS=equipos.get(i).getNombre()+"VS"+equipos.get(j+i).getNombre();
                partidos.add(new Partido(1,2,2000,equipos.get(i).getHorario(),VS));

            }
        }
    }
    return partidos;
}
}
