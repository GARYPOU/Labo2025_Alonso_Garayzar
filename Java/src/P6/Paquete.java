package P6;

import java.io.Serializable;

public class Paquete implements Serializable {
    private String tipo;      // "REGISTER", "CLAVE_AES", "MENSAJE", "ACK", "CLAVE_PUBLICA"
    private byte[] datos;     // Contenido cifrado o datos
    private byte[] iv;        // IV para AES (si aplica)
    private String origen;    // Agente, Servidor, Cliente...
    private byte[] firma;     // Firma digital

    public Paquete(String tipo, byte[] datos, byte[] iv, String origen) {
        this.tipo = tipo;
        this.datos = datos;
        this.iv = iv;
        this.origen = origen;
        this.firma = null;
    }

    public Paquete(String tipo, byte[] datos, byte[] iv, String origen, byte[] firma) {
        this.tipo = tipo;
        this.datos = datos;
        this.iv = iv;
        this.origen = origen;
        this.firma = firma;
    }

    public String getTipo() { return tipo; }
    public byte[] getDatos() { return datos; }
    public byte[] getIv() { return iv; }
    public String getOrigen() { return origen; }
    public byte[] getFirma() { return firma; }
    public void setFirma(byte[] firma) { this.firma = firma; }
}

