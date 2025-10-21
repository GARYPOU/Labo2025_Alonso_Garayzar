package P3;

import p2.crypto.CryptoUtils;
import p2.crypto.KeyLoader;
import p2.seguridad.MensajeSeguro;
import p2.seguridad.AckMessage;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

/**
 * Cliente genérico (ej: cliente1 o cliente2). Escucha en un puerto UDP,
 * recibe MensajeSeguro, descifra la clave AES con su privada, descifra el mensaje,
 * verifica integridad y envía ACK firmado al servidor.
 *
 * Ejecutar 2 instancias con argumentos: Cliente <clienteId> <puerto>
 * Ejemplo:
 *   java -cp out p2.Cliente cliente1 6001
 *   java -cp out p2.Cliente cliente2 6002
 */
public class Cliente {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Uso: java p2.Cliente <clienteId> <puerto>");
            return;
        }
        String clienteId = args[0]; // "cliente1" o "cliente2"
        int puerto = Integer.parseInt(args[1]);

        // Cargar clave privada y pública local (privada para firmar, pública del servidor no necesaria aquí)
        PrivateKey priv = KeyLoader.loadPrivateKey(clienteId + "_priv.der");
        // PublicKey clientPub = KeyLoader.loadPublicKey(clienteId + "_pub.der"); // si se requiere

        DatagramSocket socket = new DatagramSocket(puerto);
        System.out.println(clienteId + ": escuchando en puerto " + puerto);

        byte[] buf = new byte[65535];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // Deserializar MensajeSeguro
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();

            if (obj instanceof MensajeSeguro) {
                MensajeSeguro ms = (MensajeSeguro) obj;
                System.out.println(clienteId + ": MensajeSeguro recibido del servidor.");

                // Descifrar la clave AES con la privada del cliente
                byte[] claveAesBytes = CryptoUtils.descifrarRSA(ms.getClaveAesCifrada(), priv);
                SecretKey aesKey = CryptoUtils.bytesASecretKey(claveAesBytes);

                // Descifrar mensaje AES
                byte[] mensajeDesc = CryptoUtils.descifrarAES(ms.getMensajeCifrado(), aesKey, ms.getIv());
                String texto = new String(mensajeDesc, StandardCharsets.UTF_8);
                System.out.println(clienteId + ": Mensaje descifrado: " + texto);

                // Verificar integridad con hash
                byte[] hashLocal = CryptoUtils.hashSHA256(mensajeDesc);
                boolean integra = java.security.MessageDigest.isEqual(hashLocal, ms.getHashMensaje());
                System.out.println(clienteId + ": Integridad = " + integra);

                // Construir ACK firmado
                String ackText = "ACK recibido por " + clienteId;
                byte[] firma = CryptoUtils.sign(ackText.getBytes(StandardCharsets.UTF_8), priv);
                AckMessage ack = new AckMessage(ackText, firma, clienteId);

                // Serializar y enviar ACK al servidor (suponemos server en localhost:5555)
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(ack);
                oos.flush();
                byte[] datos = baos.toByteArray();

                InetAddress serverAddr = InetAddress.getByName("127.0.0.1");
                DatagramPacket ackPacket = new DatagramPacket(datos, datos.length, serverAddr, 5555);
                socket.send(ackPacket);
                System.out.println(clienteId + ": ACK enviado y firmado.");
            } else {
                System.out.println(clienteId + ": objeto recibido no es MensajeSeguro.");
            }
        }
    }
}
