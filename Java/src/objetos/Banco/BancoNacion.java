package objetos.Banco;

import personas.Customer;
import personas.Persona;

import java.time.LocalDate;
import java.util.ArrayList;

public class BancoNacion {
    private String direccion;
    private boolean prestamo;
    private boolean boveda;
    private ArrayList<Persona>trabajadores;

    public BancoNacion() {
        this.direccion="Juan b Justo 9100";
        this.prestamo=true;
        this.boveda=true;
        this.trabajadores=new ArrayList<>();
    }
    public BancoNacion(String direccion, boolean prestamo, boolean boveda) {
        this.direccion=direccion;
        this.prestamo=prestamo;
        this.boveda=prestamo;
        this.trabajadores=new ArrayList<>();
    }
    public void agregarTrabajador(Persona trabajador) {
        Persona p1 = new Customer();

        trabajadores.add(trabajador);
    }
    public int cantidadTrabajadores(){
        int cant=0;
        for(int i=0; i<trabajadores.size();i++){
            cant=i;
        }
        return cant;
    }
    public Persona empleadoMayorAntiguedad(){
        Persona per = trabajadores.getFirst();
        LocalDate fecha=LocalDate.of(1,1,1);
        for(Persona p: trabajadores) {
            if(fecha.getYear()<p.antiguedad().getYear()){
                fecha=p.antiguedad();
                per=p;
            }
            else if(fecha.getMonthValue()<p.antiguedad().getMonthValue()){
                fecha=p.antiguedad();
                per=p;
            }
            else if(fecha.getDayOfMonth()<p.antiguedad().getDayOfMonth()){
                fecha=p.antiguedad();
                per=p;
            }

        }
        return per;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isPrestamo() {
        return prestamo;
    }

    public void setPrestamo(boolean prestamo) {
        this.prestamo = prestamo;
    }

    public boolean isBoveda() {
        return boveda;
    }

    public void setBoveda(boolean boveda) {
        this.boveda = boveda;
    }

    public ArrayList<Persona> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(ArrayList<Persona> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public static void main(String[] args) {

    }
}