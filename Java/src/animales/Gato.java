package animales;

public class Gato extends Mascota {

    public Gato(String nombre, String dueño, Saludo saludo, int alegria) {
        super(nombre, dueño, saludo, alegria);
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
            saludin=saludin+Saludo.MIAU;
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
    void alimentar() {
        int ale=getAlegria()+1;
        setAlegria(ale);
    }


}
