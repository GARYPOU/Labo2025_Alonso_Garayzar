package objetos;

public enum Color {
    AZUL("0000FF"),
    ROJO("FF0000"),
    AMARILLO("00FF00");

    private String colores;

    Color(String colores) {
        this.colores=colores;
    }
}
