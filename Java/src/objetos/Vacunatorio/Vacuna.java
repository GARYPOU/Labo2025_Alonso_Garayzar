package objetos.Vacunatorio;

import java.time.LocalDate;

public class Vacuna {
    private LocalDate fecha_fabricacion;
    private LocalDate fecha_aplicacion;
    private int n_lote;
    private int n_fabricion;
    private String comercial;

    public Vacuna(LocalDate fecha_fabricacion, LocalDate fecha_aplicacion, int n_lote, int n_fabricion, String comercial) {
        this.fecha_fabricacion = fecha_fabricacion;
        this.fecha_aplicacion = fecha_aplicacion;
        this.n_lote = n_lote;
        this.n_fabricion = n_fabricion;
        this.comercial = comercial;
    }

    public LocalDate getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(LocalDate fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public LocalDate getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(LocalDate fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public int getN_lote() {
        return n_lote;
    }

    public void setN_lote(int n_lote) {
        this.n_lote = n_lote;
    }

    public int getN_fabricion() {
        return n_fabricion;
    }

    public void setN_fabricion(int n_fabricion) {
        this.n_fabricion = n_fabricion;
    }

    public String getComercial() {
        return comercial;
    }

    public void setComercial(String comercial) {
        this.comercial = comercial;
    }
}
