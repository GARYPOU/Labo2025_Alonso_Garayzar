package personas;
import Fechas.Fecha;
import objetos.Nota;

import java.util.ArrayList;

public class Alumno {
    private String nombre;
    private String apellido;
    private Fecha fechanacimiento;
    private ArrayList<Nota> notas = new ArrayList<Nota>();

    public Alumno(){
        this.nombre="Pepe";
        this.apellido="Alonso";
        this.fechanacimiento.setAnio(2024);
        this.fechanacimiento.setMes(12);
        this.fechanacimiento.setDia(31);
        this.notas.add(new Nota(2,"Historia"));

    }
    public Alumno(String nombre, String apellido, Fecha fechanacimiento, ArrayList<Nota> notas){
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechanacimiento=fechanacimiento;
        this.notas=notas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Fecha getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Fecha fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {

        this.notas = notas;
    }
    public void agregarNotas(){

        ArrayList<Nota> n1 = new ArrayList<Nota>();
        ArrayList<Nota> n2 = new ArrayList<Nota>();
        n1.add(new Nota(1,"Mates"));
        n2.add(new Nota(3,"Historia"));
        Alumno a1 = new Alumno("Jorge","Ruiz",new Fecha(1,3,2002),new ArrayList<Nota>(n1));
        Alumno a2 = new Alumno("mati","Rui",new Fecha(5,8,2045),new ArrayList<Nota>(n2));
    }
    public int mayorNota(){
        Nota notaTemp;
        int num=0;
        for (int i = 0; i < this.notas.size(); i++) {

            if(this.notas.get(i).getNota()<this.notas.get(i+1).getNota()){
                notaTemp=this.notas.get(i);
                this.notas.set(1,this.notas.get(i+1));
                this.notas.set(i+1,notaTemp);
                num=i;
            }
        }
        System.out.println("mayor nota:"+this.notas.get(0));
        return num;

    }
        public void mnorNota(){
            System.out.println("menor nota:"+this.notas.get(mayorNota()));
        
        }



    public static void main(String[] args) {

    }
}

