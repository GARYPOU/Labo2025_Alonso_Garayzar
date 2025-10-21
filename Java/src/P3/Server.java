package P3;

import P3.Encriptador;
import P3.KeyLoader;
import P3.MensajeSeguro;
import P3.AckMessage;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Server: recibe MensajeSeguro del Agente por UDP, descifra la clave AES con su privada,
 * descifra mensaje AES, verifica hash. Luego reenvía a clientes cifrando la misma clave AES
 * con la pública de cada cliente. Además recibe ACKs firmados y verifica firmas.
 */
public class Server {

    public static void main(String[] args) throws Exception {
        int serverPort = 5555;
        DatagramSocket socket = new DatagramSocket(serverPort);

        // Cargar clave privada del servidor
        PrivateKey serverPriv = KeyLoader.loadPrivateKey("server_priv.der");

        // Mapa con información de clientes:
        // clienteId -> {addr, puerto, publicKey}
        // En este ejemplo simplificado asumimos que los clientes corren en localhost en puertos 6001 y 6002
        Map<String, InetAddress> clientAddr = new HashMap<>();
        Map<String, Integer> clientPort = new HashMap<>();
        Map<String, PublicKey> clientPub = new HashMap<>();

        clientAddr.put("cliente1", InetAddress.getByName("127.0.0.1"));
        clientPort.put("cliente1", 6001);
        clientPub.put("cliente1", KeyLoader.loadPublicKey("cliente1_pub.der"));

        clientAddr.put("cliente2", InetAddress.getByName("127.0.0.1"));
        clientPort.put("cliente2", 6002);
        clientPub.put("cliente2", KeyLoader.loadPublicKey("cliente2_pub.der"));

        System.out.println("Server: esperando MensajeSeguro en puerto " + serverPort);

        byte[] buf = new byte[65535]; // Tamaño máximo razonable para UDP; mensajes grandes pueden fragmentarse
        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // Deserializar objeto recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();

            if (obj instanceof MensajeSeguro) {
                MensajeSeguro ms = (MensajeSeguro) obj;
                System.out.println("Server: MensajeSeguro recibido de " + ms.getOrigenId());

                // Descifrar clave AES con private key del servidor
                byte[] claveAesBytes = Encriptador.descifrarRSA(ms.getClaveAesCifrada(), serverPriv);
                SecretKey aesKey = Encriptador.bytesASecretKey(claveAesBytes);

                // Descifrar mensaje AES
                byte[] mensajeDesc = Encriptador.descifrarAES(ms.getMensajeCifrado(), aesKey, ms.getIv());
                String texto = new String(mensajeDesc, StandardCharsets.UTF_8);
                System.out.println("Server: Mensaje descifrado: " + texto);

                // Verificar integridad con hash
                byte[] hashLocal = Encriptador.hashSHA256(mensajeDesc);
                if (MessageDigest.isEqual(hashLocal, ms.getHashMensaje())) {
                    System.out.println("Server: Integridad verificada ✅");
                } else {
                    System.out.println("Server: ¡Integridad fallida! ⚠️");
                }

                // REENVIO A CLIENTES: cifrar la misma clave AES con la pública de cada cliente
                for (String clienteId : clientPub.keySet()) {
                    PublicKey pub = clientPub.get(clienteId);
                    byte[] claveParaCliente = Encriptador.cifrarRSA(claveAesBytes, pub);

                    // Construir MensajeSeguro para el cliente: no incluimos mensajePlano
                    MensajeSeguro msParaCliente = new MensajeSeguro(
                            null,
                            ms.getMensajeCifrado(),
                            ms.getHashMensaje(),
                            claveParaCliente,
                            ms.getIv(),
                            "SERVER"
                    );

                    // Serializar y enviar por UDP
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(msParaCliente);
                    oos.flush();
                    byte[] datos = baos.toByteArray();

                    InetAddress addr = clientAddr.get(clienteId);
                    int port = clientPort.get(clienteId);
                    DatagramPacket p = new DatagramPacket(datos, datos.length, addr, port);
                    socket.send(p);

                    System.out.println("Server: reenviado MensajeSeguro a " + clienteId + " (" + addr + ":" + port + ")");
                }

                // Esperar ACKs (opcional: en sistemas reales se usaría un hilo aparte para ACKs)
                // Aquí procesamos ACKs si llegan (no bloqueante: timeout breve)
                socket.setSoTimeout(2000); // 2s para recibir ACKs de ejemplo
                try {
                    byte[] ackBuf = new byte[8192];
                    DatagramPacket ackPacket = new DatagramPacket(ackBuf, ackBuf.length);
                    while (true) {
                        socket.receive(ackPacket);
                        ByteArrayInputStream bais2 = new ByteArrayInputStream(ackPacket.getData(), 0, ackPacket.getLength());
                        ObjectInputStream ois2 = new ObjectInputStream(bais2);
                        Object ackObj = ois2.readObject();
                        if (ackObj instanceof AckMessage) {
                            AckMessage ack = (AckMessage) ackObj;
                            String clienteId = ack.getClienteId();
                            System.out.println("Server: ACK recibido de " + clienteId + " : " + ack.getAckText());
                            // Verificar firma usando la clave pública del cliente
                            PublicKey pubCliente = clientPub.get(clienteId);
                            boolean ok = Encriptador.verify(ack.getAckText().getBytes(StandardCharsets.UTF_8), ack.getFirma(), pubCliente);
                            if (ok) {
                                System.out.println("Server: Firma de ACK verificada ✅");
                            } else {
                                System.out.println("Server: Firma de ACK inválida ❌");
                            }
                        }
                    }
                } catch (java.net.SocketTimeoutException ste) {
                    // Tiempo de espera agotado para ACKs en este ciclo
                    socket.setSoTimeout(0); // quitar timeout
                }
            } else {
                System.out.println("Server: Objeto recibido no es MensajeSeguro.");
            }
        }
    }
}
