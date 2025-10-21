package P3;

import java.io.Serializable;

/**
 * Mensaje de ACK firmado por el cliente.
 * - ackText: texto de confirmación
 * - firma: firma digital sobre ackText bytes
 * - clienteId: identificador del cliente (ej: "cliente1")
 */
public class AckMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ackText;
    private byte[] firma;
    private String clienteId;

    public AckMessage(String ackText, byte[] firma, String clienteId) {
        this.ackText = ackText;
        this.firma = firma;
        this.clienteId = clienteId;
    }

    public String getAckText() { return ackText; }
    public byte[] getFirma() { return firma; }
    public String getClienteId() { return clienteId; }
}
