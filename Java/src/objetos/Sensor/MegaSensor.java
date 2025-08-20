package objetos.Sensor;

import java.time.LocalDate;

public class MegaSensor extends Sensor{
    private Humo humin;
    private Temperatura tempe;
    private Precion precio;

    public MegaSensor(boolean estado, double medida, LocalDate adquisicion, double umbralInicial, String nombre, Humo humin, Temperatura tempe, Precion precio) {
        super(estado, medida, adquisicion, umbralInicial, nombre);
        this.humin = humin;
        this.tempe = tempe;
        this.precio = precio;
    }

    public MegaSensor(Humo humin, Temperatura tempe, Precion precio) {
        this.humin = humin;
        this.tempe = tempe;
        this.precio = precio;
    }

    public Humo getHumin() {
        return humin;
    }

    public void setHumin(Humo humin) {
        this.humin = humin;
    }

    public Temperatura getTempe() {
        return tempe;
    }

    public void setTempe(Temperatura tempe) {
        this.tempe = tempe;
    }

    public Precion getPrecio() {
        return precio;
    }

    public void setPrecio(Precion precio) {
        this.precio = precio;
    }
}
