package objetos.BibliotecaVirtual;

public enum Tipo_membresia {
    BRONCE(5), PLATA(15), ORO(50);

    private int cant;

    Tipo_membresia(int cant) {
        this.cant = cant;
    }
    public int getTipo_membresia() {
        return cant;
    }
}
