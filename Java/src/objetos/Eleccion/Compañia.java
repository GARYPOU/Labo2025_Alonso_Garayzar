package objetos.Eleccion;

public enum Compañia {
    CLARO(50), PERSONAL(100), MOVISTAR(30), TUENTI(200);


    private int saldo;

    private Compañia(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
