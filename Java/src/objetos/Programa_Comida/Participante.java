package objetos.Programa_Comida;

import objetos.Color;
import personas.Persona;

public abstract class Participante extends Persona {
    private String Localidad;
    private Color color;



    public abstract void preparar();
    public abstract void ccinar(Plato p);

}
