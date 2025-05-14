package personas;

import objetos.Nota;
import objetos.Materia;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Alumno extends Persona {


    private ArrayList<Nota> notas ;
    private ArrayList<Materia> materias ;
    Period diff = this.getNacimiento().until(LocalDate.now());
    private int edad = diff.getYears();

    public Alumno(){
        super();
        this.notas.add(new Nota(2,"Historia"));

    }
    public Alumno(String nombre, String apellido, LocalDate fechanacimiento, ArrayList<Nota> notas){
        super(nombre, apellido, fechanacimiento);
        this.notas=notas;
    }



    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {

        this.notas = notas;
    }

    public int getEdad() {
        return edad;
    }

    public void agregarNotas(){

        ArrayList<Nota> n1 = new ArrayList<Nota>();
        ArrayList<Nota> n2 = new ArrayList<Nota>();
        n1.add(new Nota(1,"Mates"));
        n2.add(new Nota(3,"Historia"));

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

    public void agregaralumno(Materia m1){
        this.materias.add(m1);
        m1.agregar(this);


    }
   public void promNotas(){
       for (int i = 0; i < notas.size(); i++) {
           int notastotal=0;
           notastotal=notas.get(i).getNota();

       }
   }




    public static void main(String[] args) {
        Materia m1 = new Materia();
    }
}

