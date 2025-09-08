package Arco_Flecha;

public enum Colores {
    ROJO(4),VERDE(3),AMARRILLO(5),BLANCO(1),NEGRO(2);

    private int puntos;

    private Colores(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;


    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
