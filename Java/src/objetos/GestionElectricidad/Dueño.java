package objetos.GestionElectricidad;

import personas.Persona;

public class Dueño extends Persona {
    private int dni;

    public Dueño(String nom, String apellido, int dni) {
        super(nom, apellido);
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
