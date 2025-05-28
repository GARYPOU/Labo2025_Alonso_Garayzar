package objetos;

import java.util.ArrayList;

public class Computadora {
    ArrayList<Dispositivo>dispositivos;
    ArrayList<Componente>componentes;

    public Computadora(){
        this.dispositivos=new ArrayList<>();
        this.componentes=new ArrayList<>();
    }
}
