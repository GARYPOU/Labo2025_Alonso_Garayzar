package objetos;

import personas.Persona;

public class Compra {
    private Persona cliente;
    private String pago;
    private int cant;

    public Compra(Persona cliente, String pago, int cant) {
        this.cliente = cliente;
        this.pago = pago;
        this.cant = cant;
    }
    public Compra() {
        this.cliente = new Persona();
        this.pago = "Tarjeta";
        this.cant = 4;
    }
}
