package objetos.GestionSangre;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class SistemaTratamientos {
    private HashMap<Tratamiento, LocalDate> tratamientos;
    private HashSet<Pasciente> pascientes;


    public void realizarTratamiento() {
        for (Map.Entry<Tratamiento, LocalDate> t : tratamientos.entrySet()) {
            if (t.getKey().tratamiento()) {
                t.getValue().plusDays(1);
            }
        }
    }

    public int cantPascientes() {
        return pascientes.size() - tratamientos.size();
    }
}
