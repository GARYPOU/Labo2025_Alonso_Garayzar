package test;

import objetos.Llamada;
import objetos.SistemaLlamada;
import personas.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLlamada {

    private SistemaLlamada sistema;
    private Empleado e1, e2, e3;
    private ArrayList<Llamada> llamadas;

    @BeforeEach
    public void setUp() {
        e1 = new Empleado("Juan", "Pérez", 123, LocalDate.of(1990, 1, 1), 1111, "Argentina", 1001);
        e2 = new Empleado("Ana", "Lopez", 456, LocalDate.of(1985, 5, 10), 2222, "Chile", 1002);
        e3 = new Empleado("Carlos", "Mora", 789, LocalDate.of(1992, 3, 15), 3333, "Argentina", 1003);
        ArrayList<Empleado> todos = new ArrayList<>();
        todos.add(e1);
        todos.add(e2);
        todos.add(e3);
        sistema.setEmpleados(todos);

        llamadas = new ArrayList<>();

        llamadas.add(new Llamada(e1, e2, LocalDateTime.now(), 120));
        llamadas.add(new Llamada(e1, e3, LocalDateTime.now(), 60));
        llamadas.add(new Llamada(e2, e1, LocalDateTime.now(), 300));
        llamadas.add(new Llamada(e3, e2, LocalDateTime.now(), 180)); 





        sistema.getLlamadas().get(0).getOrigen().getLlamadas().clear();
        sistema.getLlamadas().get(1).getOrigen().getLlamadas().clear();
        sistema.getLlamadas().get(2).getOrigen().getLlamadas().clear();
        sistema.getLlamadas().get(3).getOrigen().getLlamadas().clear();

        sistema.llamasEmple();

    }

    @Test
    public void testCantidadLlamadasEmpleado() {
        assertEquals(2, e1.getLlamadas().size());
        assertEquals(1, e2.getLlamadas().size());
        assertEquals(1, e3.getLlamadas().size());
    }

    @Test
    public void testRankingExterior() {
        ArrayList<Empleado> ranking = sistema.exterior();


        assertEquals(e2.getDni(), ranking.get(0).getDni());
        assertEquals(e3.getDni(), ranking.get(1).getDni());
        assertEquals(e1.getDni(), ranking.get(2).getDni());
    }
}
