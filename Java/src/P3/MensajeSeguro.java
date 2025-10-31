package P3;

import java.io.Serializable;

public class MensajeSeguro implements Serializable {
    private static final long serialVersionUID = 1L;

    private byte[] mensajePlano;       // Mensaje original (en bytes)
    private byte[] mensajeCifrado;     // Mensaje cifrado con AES
    private byte[] hashMensaje;        // Hash SHA-256 del mensaje original
    private byte[] claveAesCifrada;    // Clave AES cifrada con RSA
    private byte[] iv;                 // Vector de inicialización para AES

    public MensajeSeguro(byte[] mensajePlano, byte[] mensajeCifrado, byte[] hashMensaje,
                         byte[] claveAesCifrada, byte[] iv, String agente) {
        this.mensajePlano = mensajePlano;
        this.mensajeCifrado = mensajeCifrado;
        this.hashMensaje = hashMensaje;
        this.claveAesCifrada = claveAesCifrada;
        this.iv = iv;
    }

    public byte[] getMensajePlano() { return mensajePlano; }
    public byte[] getMensajeCifrado() { return mensajeCifrado; }
    public byte[] getHashMensaje() { return hashMensaje; }
    public byte[] getClaveAesCifrada() { return claveAesCifrada; }
    public byte[] getIv() { return iv; }

    // Getters auxiliares si querés debuggear el contenido como string
    public String getMensajeTexto() { return new String(mensajePlano); }



}
