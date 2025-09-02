package objetos.Bebidas;
import personas.Bebedor;
import java.util.ArrayList;

public class Bar {
    private ArrayList<Bebedor>bebedores;
    private ArrayList<Bebida>bebidas;




    public Bar(ArrayList<Bebedor>bebedores, ArrayList<Bebida>bebidas) {
        this.bebedores=bebedores;
        this.bebidas=bebidas;
    }
    public void calcularBebedor(){
        int total=0;
        for (Bebedor Beb: bebedores){
            for (Bebida b: Beb.getBebidas()){
                total=total+b.calcularCoficiente();
            }
            Beb.setCoeficienteHidratacion(total);
            System.out.println("El cliente fue:"+Beb.getNombre()+"y su coeficiente de hidratacion fue de:"+total);
            total=0;
        }

    }
    public Bebedor mejorCoeficiente(){
        if (bebedores.isEmpty()){
            throw new PersonaNoRegistradaException("No hay personas registradas");
        }
        Bebedor beb = new Bebedor();
        for(Bebedor b : bebedores){
            if(b.getCoeficienteHidratacion()>beb.getCoeficienteHidratacion()){
                beb=b;
            }
        }
        return beb;
    }
    public Bebedor peorCoeficiente(){
        if (bebedores.isEmpty()){
            throw new PersonaNoRegistradaException("No hay personas registradas");
        }

        Bebedor beb = new Bebedor();
        for(Bebedor b : bebedores){
            if(b.getCoeficienteHidratacion()<beb.getCoeficienteHidratacion()){
                beb=b;
            }
        }
        return beb;
    }
    public Bebida elegirBebida(int pos, int cant){
        System.out.println("Lista de bebidas");
        int cont=1;
        for (Bebida b : bebidas){
            System.out.println(cont+")"+b.getNombre());
            cont=cont+1;
            
            if(b.getCantidad()>cant){
                throw new SinStockException("No hay suficientes bebidas");
            }


        }

        cantBebidas(cant);
        return bebidas.get(pos);


    }

    public void agregarBebedor(Bebedor nuevo) throws DniDuplicadoException {
        for (Bebedor b : bebedores) {
            if (b.getDni() == nuevo.getDni()) {
                throw new DniDuplicadoException("Ya existe una persona con el DNI " + nuevo.getDni());
            }
        }
        bebedores.add(nuevo);
    }
    public int cantBebidas(int cant){
        return cant;
    }

}
