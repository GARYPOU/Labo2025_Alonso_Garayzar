package objetos;

public class Nota {
    private int nota;
    private String materia;




    public Nota(){
        this.nota=1;
        this.materia="matematica";
    }
    public Nota(int nota, String materia){
        this.nota=nota;
        this.materia=materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }


    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }


    public static void main(String[] args) {
        Nota nota1 = new Nota(3,"Ingles");
        Nota nota2 = new Nota(10,"Italiano");
    }
}
