package P1;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Server {
    private static final int PUERTO_SERVIDOR = 5000;

    private static final List<SocketAddress> clientes = new ArrayList<>();
    private static final Map<SocketAddress, Boolean> ackRecibidos = new ConcurrentHashMap<>();

    // Claves
    private static KeyPair parRSA;
    private static SecretKey claveAES; // compartida

    private static void manejarCliente(DatagramSocket socket, byte[] mensajeCifrado, SocketAddress cliente) {
        try {
            while (!ackRecibidos.get(cliente)) {
                DatagramPacket paquete = new DatagramPacket(mensajeCifrado, mensajeCifrado.length,
                        ((InetSocketAddress)cliente).getAddress(),
                        ((InetSocketAddress)cliente).getPort());
                socket.send(paquete);
                System.out.println("Servidor: Mensaje cifrado enviado a " + cliente);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Generar par de claves RSA
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(2048);
            parRSA = gen.generateKeyPair();

            DatagramSocket socketServidor = new DatagramSocket(PUERTO_SERVIDOR);
            byte[] buffer = new byte[4096];

            System.out.println("Servidor esperando registros de clientes...");

            while (clientes.size() < 2) {
                DatagramPacket registro = new DatagramPacket(buffer, buffer.length);
                socketServidor.receive(registro);
                String mensaje = new String(registro.getData(), 0, registro.getLength()).trim();

                if (mensaje.equalsIgnoreCase("REGISTER")) {
                    SocketAddress clienteAddr = new InetSocketAddress(registro.getAddress(), registro.getPort());
                    if (!clientes.contains(clienteAddr)) {
                        clientes.add(clienteAddr);
                        ackRecibidos.put(clienteAddr, false);
                        System.out.println("Servidor: Cliente registrado -> " + clienteAddr);

                        // Enviar clave pública RSA al cliente
                        byte[] clavePublicaBytes = parRSA.getPublic().getEncoded();
                        DatagramPacket paqueteClave = new DatagramPacket(clavePublicaBytes, clavePublicaBytes.length,
                                ((InetSocketAddress)clienteAddr).getAddress(),
                                ((InetSocketAddress)clienteAddr).getPort());
                        socketServidor.send(paqueteClave);
                    }
                } else {
                    // Si el mensaje recibido es la clave AES cifrada
                    byte[] claveCifrada = Arrays.copyOf(registro.getData(), registro.getLength());
                    Cipher rsa = Cipher.getInstance("RSA");
                    rsa.init(Cipher.DECRYPT_MODE, parRSA.getPrivate());
                    byte[] claveDescifrada = rsa.doFinal(claveCifrada);

                    // Guardar clave AES (todos usan la misma)
                    claveAES = new SecretKeySpec(claveDescifrada, "AES");
                    System.out.println("Servidor: Clave AES compartida establecida.");
                }
            }

            // Recibir mensaje del Agente
            DatagramPacket paqueteEntrante = new DatagramPacket(buffer, buffer.length);
            socketServidor.receive(paqueteEntrante);
            String mensajeAgente = new String(paqueteEntrante.getData(), 0, paqueteEntrante.getLength());
            System.out.println("Servidor: Mensaje recibido del Agente: " + mensajeAgente);

            // Cifrar con AES
            Cipher aes = Cipher.getInstance("AES");
            aes.init(Cipher.ENCRYPT_MODE, claveAES);
            byte[] mensajeCifrado = aes.doFinal(mensajeAgente.getBytes());

            for (SocketAddress cliente : clientes) {
                new Thread(() -> manejarCliente(socketServidor, mensajeCifrado, cliente)).start();
            }

            // Esperamos ACKs
            while (ackRecibidos.values().contains(false)) {
                DatagramPacket respuesta = new DatagramPacket(new byte[4096], 4096);
                socketServidor.receive(respuesta);
                SocketAddress remitente = new InetSocketAddress(respuesta.getAddress(), respuesta.getPort());

                // Descifrar ACK
                byte[] datosCifrados = Arrays.copyOf(respuesta.getData(), respuesta.getLength());
                aes.init(Cipher.DECRYPT_MODE, claveAES);
                String ack = new String(aes.doFinal(datosCifrados));

                if (ack.equalsIgnoreCase("ACK") && ackRecibidos.containsKey(remitente)) {
                    ackRecibidos.put(remitente, true);
                    System.out.println("Servidor: ACK recibido de " + remitente);
                }
            }

            System.out.println("Servidor: Todos los ACKs recibidos. Cerrando servidor.");
            socketServidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

