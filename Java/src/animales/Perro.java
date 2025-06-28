package animales;

public class Perro extends Mascota{

    public Perro(String nombre, String dueño, Saludo saludo,int alegria) {
        super(nombre, dueño, saludo, alegria);
    }
    public Perro() {
        super("Teo","Gary",Saludo.GUAU,0);

    }
    public void chequearAlegria(){
        if(getAlegria()<=0){
            setAlegria(1);
        }
    }

    @Override
    String saludar(String nombre, String dueño) {
        String saludin=" ";
        int cont=1;
        while(cont<getAlegria()){
            saludin=saludin+getSaludo();
            cont++;
        }
        if (nombre.equals(getNombre()) && dueño.equals(getDueño())) {
            return saludin;
        } else {
            String saludini = saludin.toUpperCase()+"!";
            return saludini;
        }
    }
    @Override
    String obtenerClase() {
        return "Perro";
    }


    @Override
    void alimentar() {
        int ale=getAlegria()+1;
        setAlegria(ale);
    }

}
