package objetos;

import java.util.ArrayList;

public class Computadora {
    private ArrayList<Dispositivo>dispositivos;
    private CPU procesador;

    public Computadora(CPU procesador){
        this.dispositivos=new ArrayList<>();
        this.procesador=procesador;
    }
    public Computadora(CPU cpu, ArrayList<Dispositivo> dispositivos) {
        if (cpu == null) throw new IllegalArgumentException("Debe incluir una CPU");
        boolean tienePuertosEntrada = false;
        boolean tienePuertosSalida = false;
        for(Dispositivo p: dispositivos){
            if (p instanceof Disentrada){
                tienePuertosEntrada = true;
            } else if (p instanceof Dissalida){
                tienePuertosSalida = true;
            }
        }
        if (tienePuertosEntrada && tienePuertosSalida && cpu !=null){
            this.procesador = cpu;
            this.dispositivos = dispositivos;
        }
    }
    public double calcularPrecioNeto() {
        double suma = procesador.getPrecio();
        for (Dispositivo p : dispositivos) {
            suma += p.getPrecio();
        }
        return suma;
    }

    public void actualizarStock() {
        procesador.reducirStock(1);
        for (Dispositivo d : dispositivos) {
            d.reducirStock(1);
        }
    }


    public int cantEntrada () {
        int cant = 0;
        for (Dispositivo d : dispositivos) {
            if (d instanceof Disentrada) {
                cant = cant + 1;
            }
        }

        return cant;
    }
    public int cantSalida () {
        int cant = 0;
        for (Dispositivo d : dispositivos) {
            if (d instanceof Dissalida) {
                cant = cant + 1;
            }
        }

        return cant;
    }
}
