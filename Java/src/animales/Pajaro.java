package animales;

public class Pajaro extends Mascota{
    private boolean cantor;
    private String canto;
    public Pajaro(String nombre, String dueño, Saludo saludo, int alegria) {
        super(nombre, dueño, saludo, alegria);
    }



    public boolean isCantor() {
        return cantor;
    }

    public void setCantor(boolean cantor) {
        this.cantor = cantor;
    }
    public void cantor(String canto){
        if(this.cantor){
            this.canto=canto;
        }

    }
    @Override
    Saludo saludar() {
        return getSaludo();
    }

}
