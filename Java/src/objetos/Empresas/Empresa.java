package objetos.Empresas;

import personas.Empleado;

import java.util.ArrayList;

public class Empresa {
    ArrayList<Empleado>empleados;


    public Empresa(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
    public void llamadasEmpleado() {
    }

}
