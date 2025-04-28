package objetos;

public class Nota {
    private int nota;
    private String nombre_alumno;
    private String materia;




    public Nota(){
        this.nota=1;
        this.nombre_alumno="Feli";
        this.materia="matematica";
    }
    public Nota(String nombre_alumno, int nota, String materia){
        this.nota=nota;
        this.nombre_alumno=nombre_alumno;
        this.materia=materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
