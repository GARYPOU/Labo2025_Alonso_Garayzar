package objetos.Empresa;

public enum Comision {
    UX (1.08), UI(1.07),IT(1.12);

    private double valor;

    Comision(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}

