package objetos;

import personas.Persona;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private LocalDate creacion;
    private ArrayList<Plato>platos;
    private Persona solicitud;
    private LocalDate horaentrega;
    private String estado;

    public Pedido(LocalDate creacion,ArrayList<Plato>platos,Persona solicitud,LocalDate horaentrega,String estado ){
        this.creacion=creacion;
        this.horaentrega=horaentrega;
        this.estado=estado;
        this.platos=platos;
        this.solicitud=solicitud;
    }

    public LocalDate getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDate creacion) {
        this.creacion = creacion;
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
    }

    public Persona getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Persona solicitud) {
        this.solicitud = solicitud;
    }

    public LocalDate getHoraentrega() {
        return horaentrega;
    }

    public void setHoraentrega(LocalDate horaentrega) {
        this.horaentrega = horaentrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public void agregarplatos(Plato p){
        platos.add(p);
    }

    public void modificarplatos(Plato p){
        platos.set(1,p);
    }

    public void eliminarplaros(Plato p){
        platos.remove(p);
    }
}


