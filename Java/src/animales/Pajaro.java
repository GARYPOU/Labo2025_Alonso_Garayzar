package animales;

public class Pajaro extends Mascota{
    private boolean cantor;
    private static String canto=Saludo.PIO.getSaludo();
    public Pajaro(String nombre, String dueño, Saludo saludo, int alegria, boolean cantor) {
        super(nombre, dueño, saludo, alegria);
        this.cantor=cantor;
    }
    public Pajaro() {
        super("Jaima","Juan",Saludo.PIO,0);
        cantor=true;
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

    public void chequearAlegria(){
        if(getAlegria()<=0){
            setAlegria(1);
        }
    }

    @Override
    String saludar(String nombre, String dueño) {
        if(nombre.equals(getNombre()) && dueño.equals(getDueño())){
            String cantos=" ";
            int cont=1;
            while(cont<getAlegria()){
                cantos=cantos+canto;
                cont++;
            }
            int ale=getAlegria()-1;
            setAlegria(ale);
            chequearAlegria();
            return cantos;


        }
        else{
            return "";
        }

    }
    @Override
    String obtenerClase() {
        return "Pajaro";
    }
    @Override
    void alimentar() {
        int ale=getAlegria()+1;
        setAlegria(ale);
    }



}
