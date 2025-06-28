package objetos.Banco;

import personas.Customer;
import personas.Persona;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaBancoNacion{
    ArrayList<BancoNacion>surcusales;

    public SistemaBancoNacion(){

    }


public static void main(String[] args) {



    BancoNacion sucursalUrquiza = new BancoNacion("Av. Triunvirato 3450", true, false);
    BancoNacion sucursalPueyrredon = new BancoNacion("Cochrane 2874", true, true);

    ArrayList<BancoNacion> sucursales = new ArrayList<>();

    sucursales.add(sucursalUrquiza);
    sucursales.add(sucursalPueyrredon);



    sucursalPueyrredon.cantidadTrabajadores();

    for (BancoNacion b : sucursales) {
        Persona p = b.empleadoMayorAntiguedad();
        System.out.println(p.getNombre() + " " + p.antiguedad());
    }
}
}