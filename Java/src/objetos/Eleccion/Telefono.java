package objetos.Eleccion;

public class Telefono implements AccionesCompañias{
    private int numero_serie;
    private String fabricante;
    private String modelo;
    private Compañia compañia;
    private int numero;
    private boolean prendido;

    public Telefono(int numero_serie, String fabricante, String modelo, Compañia compañia, int numero, boolean prendido) {
        this.numero_serie = numero_serie;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.compañia = compañia;
        this.numero = numero;
        this.prendido=prendido;
    }

    public int getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(int numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Compañia getCompañia() {
        return compañia;
    }

    public void setCompañia(Compañia compañia) {
        this.compañia = compañia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String mensaje() {
        if(compañia.getSaldo()>0 && prendido){
            return "Conectando con la antena más cercana";
        }
        return null;
    }
}
