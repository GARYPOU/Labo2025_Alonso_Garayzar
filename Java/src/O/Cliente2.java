package O;
import java.net.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class Cliente2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            int puertoServidor = 5000;

            // Paso 1: Registrarse en el servidor
            String registro = "REGISTER";
            DatagramPacket paqueteRegistro = new DatagramPacket(
                    registro.getBytes(),
                    registro.length(),
                    direccionServidor,
                    puertoServidor
            );
            socket.send(paqueteRegistro);
            System.out.println("Cliente: Enviado paquete de registro al servidor.");

            // Paso 2: Recibir clave pública RSA
            byte[] buffer = new byte[4096];
            DatagramPacket paqueteClave = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteClave);
            byte[] clavePublicaBytes = Arrays.copyOf(paqueteClave.getData(), paqueteClave.getLength());

            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey clavePublica = kf.generatePublic(new java.security.spec.X509EncodedKeySpec(clavePublicaBytes));
            System.out.println("Cliente: Clave pública RSA recibida.");

            // Paso 3: Generar clave AES aleatoria y enviarla cifrada
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey claveAES = kg.generateKey();

            Cipher rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, clavePublica);
            byte[] claveCifrada = rsa.doFinal(claveAES.getEncoded());

            DatagramPacket paqueteAES = new DatagramPacket(claveCifrada, claveCifrada.length, direccionServidor, puertoServidor);
            socket.send(paqueteAES);
            System.out.println("Cliente: Clave AES enviada cifrada al servidor.");

            // Paso 4: Recibir mensaje cifrado con AES
            DatagramPacket paqueteMsg = new DatagramPacket(new byte[4096], 4096);
            socket.receive(paqueteMsg);
            byte[] mensajeCifrado = Arrays.copyOf(paqueteMsg.getData(), paqueteMsg.getLength());

            Cipher aes = Cipher.getInstance("AES");
            aes.init(Cipher.DECRYPT_MODE, claveAES);
            String mensaje = new String(aes.doFinal(mensajeCifrado));
            System.out.println("Cliente: Mensaje descifrado recibido: " + mensaje);

            // Paso 5: Enviar ACK cifrado
            System.out.println("Ingrese ACK para confirmar que está activo:");
            String respuesta = scanner.nextLine().trim();

            aes.init(Cipher.ENCRYPT_MODE, claveAES);
            byte[] ackCifrado = aes.doFinal(respuesta.getBytes());

            DatagramPacket ack = new DatagramPacket(ackCifrado, ackCifrado.length, direccionServidor, puertoServidor);
            socket.send(ack);
            System.out.println("Cliente: ACK cifrado enviado.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
