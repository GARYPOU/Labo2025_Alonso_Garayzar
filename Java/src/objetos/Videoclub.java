package objetos;

import java.util.ArrayList;

public class Videoclub {
    private ArrayList<Estanteria>estanterias;
    private String comuna;
    private int codigo_postal;
    private String direccion;
    private int canEstanterias=estanterias.size();
    private String nombre;


    public Videoclub(){
        this.codigo_postal=1111;
        this.direccion="Juan b justo 9100";
        this.comuna="liniers";
        this.estanterias=new ArrayList<>();
        this.nombre="movie max";
    }

    public ArrayList<Estanteria> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(ArrayList<Estanteria> estanterias) {
        this.estanterias = estanterias;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCanEstanterias() {
        return canEstanterias;
    }

    public void setCanEstanterias(int canEstanterias) {
        this.canEstanterias = canEstanterias;
    }
    public void borrar(Estanteria estan){
        estanterias.remove(estan);

    }
    public void agregar(Estanteria estan){
        estanterias.add(estan);

    }
    public void modificar(Estanteria estan, int posEstan){

        estanterias.set(posEstan,estan);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mayorDuracion(){
        Pelicula p2 = new Pelicula();
        Estanteria e2 = new Estanteria();
        for(Estanteria e: estanterias){
            for (Pelicula p: e.peliculas){
                if(p.getDuracion()>p2.getDuracion()){
                    p2=p;
                    e2=e;
                }
            }
        }
        System.out.println("Pelicula mas larga"+p2.getNombre()+"-"+"Estanteria donde estaba"+e2.getIdentificador());
    }

    public static void main(String[] args) {
        Videoclub v1 = new Videoclub();
        Estanteria e1 = new Estanteria();
        v1.agregar(e1);
        v1.borrar(e1);
        v1.modificar(e1,1);
        v1.mayorDuracion();

    }
}
