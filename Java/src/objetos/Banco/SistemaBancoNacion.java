package objetos.Banco;

import personas.Persona;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaBancoNacion{
    ArrayList<BancoNacion>surcusales;

    public SistemaBancoNacion(){

    }


public static void main(String[] args) {

    Persona p1 = new Persona("Miguel",
            LocalDate.of(1985, 10, 12));
    Persona p2 = new Persona("Juana", "Gomez", 39987990,
            LocalDate.of(1986, 7, 28));
    Persona p3 = new Persona("Candela",
            LocalDate.of(1993, 2, 4));

    BancoNacion sucursalUrquiza = new BancoNacion("Av. Triunvirato 3450", true, false);
    BancoNacion sucursalPueyrredon = new BancoNacion("Cochrane 2874", true, true);

    ArrayList<BancoNacion> sucursales = new ArrayList<>();

    sucursales.add(sucursalUrquiza);
    sucursales.add(sucursalPueyrredon);

    sucursalUrquiza.agregarTrabajador(p1);
    sucursalUrquiza.agregarTrabajador(p3);
    sucursalPueyrredon.agregarTrabajador(p2);

    sucursalPueyrredon.cantidadTrabajadores();

    for (BancoNacion b : sucursales) {
        Persona p = b.empleadoMayorAntiguedad();
        System.out.println(p.getNombre() + " " + p.antiguedad());
    }
}
}