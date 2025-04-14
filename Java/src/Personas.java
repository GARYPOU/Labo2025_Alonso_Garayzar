public class Personas {
    private String nombre;
    private int edad;
    private String direccion;

    public Personas(){
        this.nombre="Juaita";
        this.edad=15;
        this.direccion="Urquiza toilet 9100";


    }


    public Personas(String nom, int eda, String dir){
        this.nombre=nom;
        this.edad=eda;
        this.direccion=dir;


    }



    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nom){
        this.nombre=nom;
    }

    public int getEdad(){
        return edad;
    }

    public void set(int eda){ this.edad=eda;}
    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String dir){
        this.direccion=dir;
    }



    public String mostrardatos(){
        String x;
        Personas p1 = new Personas();
        x = (String) ("Nombre:"+p1.nombre+" "+"Edad:"+p1.edad+" "+"Direccion:"+p1.direccion);
        return x;
    }




    public static void main(String[] args) {
        Personas p1 = new Personas();
        System.out.println(p1.mostrardatos());

    }
}

