package animales;

public enum Saludo {
    GUAU("guau"),MIAU("miau"),PIO("pio");

    private final String saludos;

    private Saludo(String saludo) {
        this.saludos = saludo;
    }

    public String getSaludo() {
        return saludos;
    }
}
