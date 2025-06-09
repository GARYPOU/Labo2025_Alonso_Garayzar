package objetos.Empresas;

import personas.Empleado;

import java.util.ArrayList;

public class SistemaLlamada {
    private ArrayList<Llamada> llamadas = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();


    public SistemaLlamada(ArrayList<Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    public ArrayList<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void llamasEmple() {
        for (Llamada l : this.llamadas) {
            for (Empleado e : empleados) {
                if (l.getOrigen().getDni() == e.getDni()) {
                    e.getLlamadas().add(l);
                }
            }
        }
    }


    public ArrayList<Empleado> exterior() {
        double total = 0;
        double aux = 0;
        Empleado emp = new Empleado();
        ArrayList<Empleado> emplea2 = new ArrayList<>();
        ArrayList<Double> duraciones = new ArrayList<>();

        for (Empleado emple : empleados) {
            for (Llamada l : emple.getLlamadas()) {
                if (l.getOrigen().getPais() != l.getDestino().getPais()) {
                    total = total + l.getDuracionSegundos();
                }
                emplea2.add(emple);
                duraciones.add(total);
                total = 0;
            }
            for (int i = 0; duraciones.size() > i; i++) {
                for (int j = i + 1; duraciones.size() > j; j++) {
                    if (duraciones.get(i) < duraciones.get(j)) {
                        aux = duraciones.get(i);
                        duraciones.set(i, duraciones.get(j));
                        duraciones.set(j, aux);
                        emp = emplea2.get(i);
                        emplea2.set(i, emplea2.get(j));
                        emplea2.set(j, emp);

                    }
                }


            }
        }

        return emplea2;
    }

    public void imprimirRanking() {
        for(Empleado emples: exterior()){
            System.out.println("Dni del ranking:"+emples.getDni());
        }
    }

    public void setLlamadas(ArrayList<Llamada> llamadas) {
        this.llamadas = llamadas;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}