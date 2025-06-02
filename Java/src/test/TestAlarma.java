package test;
import objetos.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.ArrayList;

import static junit.framework.Assert.*;

public class TestAlarma {

    private SistAlarmas s1;
    private Sensor sensor;
    private MegaSensor m1;
    private Precion p1;
    private Humo h1;
    private Temperatura t1;



    @Test
    public void testHumo(){
        Humo h1 = new Humo(true,30.0, LocalDate.now(),50.0);
        assertEquals(true, h1.getEstado());
        assertEquals(30.0, h1.getMedida());
        assertEquals(LocalDate.now(), h1.getAdquisicion());
        assertEquals(50.0, h1.getUmbralInicial());


    }
    @Test
    public void testTemperatura() {
        Temperatura t1 = new Temperatura(false,50.0, LocalDate.now(),90);
        assertEquals(false, t1.getEstado());
        assertEquals(50.0, t1.getMedida());
        assertEquals(90.0, t1.getUmbralInicial());
        assertEquals(LocalDate.now(), t1.getAdquisicion());
    }

    @Test
    public void testPrecion() {
        Precion p1 = new Precion(false,50.0, LocalDate.now(),90);
        assertEquals(false, p1.getEstado());
        assertEquals(50.0, p1.getMedida());
        assertEquals(90.0, p1.getUmbralInicial());
        assertEquals(LocalDate.now(), p1.getAdquisicion());
    }

    @Test
    public void testMegaSensor() {
        Humo h1 = new Humo(true,30, LocalDate.now(),50);
        Temperatura t1 = new Temperatura(false,50, LocalDate.now(),90);
        Precion p1 = new Precion(false,50, LocalDate.now(),90);
        MegaSensor m1 = new MegaSensor(false,(h1.getMedida()+t1.getMedida()+p1.getMedida())/3,LocalDate.now(),100,h1,t1,p1);
        assertEquals(false, m1.getEstado());
        assertEquals(100.0, m1.getUmbralInicial());
        assertEquals(h1, m1.getHumin());
        assertEquals(t1, m1.getTempe());
        assertEquals(p1, m1.getPrecio());
        assertEquals(LocalDate.now(), m1.getAdquisicion());
    }

    @Test
    public void testSistAlarmas() {
        SistAlarmas s1 = new SistAlarmas();
        s1.sensores = new ArrayList<>();
        s1.sensores.add(h1);
        s1.sensores.add(t1);
        s1.sensores.add(p1);
        s1.sensores.add(m1);
        assertEquals(4, s1.sensores.size());
        assertEquals(h1, s1.sensores.get(0));
        assertEquals(t1, s1.sensores.get(1));
        assertEquals(p1, s1.sensores.get(2));
        assertEquals(m1, s1.sensores.get(3));
    }
}




