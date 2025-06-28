package animales;

public class Gato extends Mascota {

    public Gato(String nombre, String dueño, Saludo saludo, int alegria) {
        super(nombre, dueño, saludo, alegria);
    }
    public Gato() {
        super("Pope","Romina",Saludo.MIAU,0);

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
        return "Gato";
    }

    @Override
    void alimentar() {
        int ale=getAlegria()+1;
        setAlegria(ale);
    }


}
