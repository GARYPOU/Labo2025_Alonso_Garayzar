package P3;

import P3.Encriptador;
import P3.KeyLoader;
import P3.MensajeSeguro;

import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

/**
 * Agente: genera una clave AES por mensaje, cifra el mensaje, calcula hash,
 * cifra la clave AES con la clave pública del servidor, y envía un MensajeSeguro por UDP.
 */
public class Agente {

    public static void main(String[] args) throws Exception {
        // Configuraciones
        String servidorHost = "127.0.0.1";
        int servidorPort = 5555;

        // Mensaje a enviar
        String texto = "Hola clientes, este mensaje viene del Agente.";

        // 1) Generar clave AES e IV
        SecretKey aes = Encriptador.generarClaveAES(128);
        byte[] iv = Encriptador.generarIV();

        // 2) Cifrar el mensaje con AES
        byte[] mensajeBytes = texto.getBytes(StandardCharsets.UTF_8);
        byte[] mensajeCifrado = Encriptador.cifrarAES(mensajeBytes, aes, iv);

        // 3) Calcular hash del mensaje (SHA-256)
        byte[] hash = Encriptador.hashSHA256(mensajeBytes);

        // 4) Cargar clave pública del servidor y cifrar la clave AES
        PublicKey serverPub = KeyLoader.loadPublicKey("server_pub.der");
        byte[] claveAesCifrada = Encriptador.cifrarRSA(Encriptador.claveAESaBytes(aes), serverPub);

        // 5) Construir MensajeSeguro
        MensajeSeguro ms = new MensajeSeguro(mensajeBytes, mensajeCifrado, hash, claveAesCifrada, iv, "AGENTE");

        // 6) Serializar y enviar por UDP
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(ms);
        oos.flush();
        byte[] datos = baos.toByteArray();

        DatagramSocket socket = new DatagramSocket();
        InetAddress addr = InetAddress.getByName(servidorHost);
        DatagramPacket packet = new DatagramPacket(datos, datos.length, addr, servidorPort);
        socket.send(packet);
        socket.close();

        System.out.println("Agente: MensajeSeguro enviado al servidor.");
    }
}
