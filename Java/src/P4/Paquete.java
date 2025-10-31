package P4;

import java.io.Serializable;

public class Paquete implements Serializable {
    private String tipo;      // "REGISTER", "CLAVE_AES", "MENSAJE", "ACK", "CLAVE_PUBLICA"
    private byte[] datos;     // Contenido cifrado o datos
    private byte[] iv;        // IV para AES (si aplica)
    private String origen;    // Agente, Servidor, Cliente...

    public Paquete(String tipo, byte[] datos, byte[] iv, String origen) {
        this.tipo = tipo;
        this.datos = datos;
        this.iv = iv;
        this.origen = origen;
    }

    public String getTipo() { return tipo; }
    public byte[] getDatos() { return datos; }
    public byte[] getIv() { return iv; }
    public String getOrigen() { return origen; }
}

