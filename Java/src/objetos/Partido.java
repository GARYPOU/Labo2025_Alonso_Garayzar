package objetos;

import java.time.LocalDate;

public class Partido {

        private LocalDate fecha;
        private Turno turno;
        private String equiposVS;

        public Partido(int i, int i1, int i2, String horario, String VS){
        this.fecha = LocalDate.of(2004,12,31);
        this.turno=Turno.MAÑANA;
        this.equiposVS="RiverVSBoca";
    }
    public Partido(LocalDate fecha, Turno turno, String equiposVS){
        this.fecha=fecha;
        this.turno=turno;
        this.equiposVS=equiposVS;
    }


}
