package objetos.Compu;
import objetos.Compu.Computadora;
import objetos.MetodoPago;
import personas.Customer;
import personas.Persona;

public class Compra {
    private Persona cliente;
    private MetodoPago pago;
    private Computadora compu;

    public Compra(Customer cliente, MetodoPago pago, Computadora compu) {
        this.cliente = cliente;
        this.pago = pago;
        this.compu=compu;
    }
    public Compra() {
        this.cliente = new Customer();
        this.pago = new MetodoPago();

    }
    public double calcularTotalConMedioDePago() {
        double base = compu.calcularPrecioNeto();
        return pago.precioFinal(base);
    }


}
