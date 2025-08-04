package objetos.Ticketvtv;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Ticket {
    private String cliente;
    private String descripcion;
    private LocalDate fcreacion;
    private int hcreacion;
    private LocalDate ffinalizacion;
    private int hfinalizacion;
    private ArrayList<String> comentarios;
    private Estado estado;
    private boolean repuesto;
}
