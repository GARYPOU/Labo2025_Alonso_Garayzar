package P4;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

public class Server {
    private static final int PUERTO_SERVIDOR = 5000;
    private static final List<SocketAddress> clientes = new ArrayList<>();
    private static final Map<SocketAddress, Boolean> ackRecibidos = new HashMap<>();
    private static final Map<SocketAddress, PublicKey> clavesPublicasClientes = new HashMap<>(); // nuevas claves públicas
    private static KeyPair parRSA;
    private static SecretKey claveAES;

    public static void main(String[] args) {
        try {
            DatagramSocket socketServidor = new DatagramSocket(PUERTO_SERVIDOR);
            parRSA = Encriptador.generarParRSA(2048);
            byte[] buffer = new byte[65535];

            System.out.println("Servidor esperando registros de clientes...");

            registrarClientes(socketServidor, buffer);

            System.out.println("Esperando clave AES del agente...");
            recibirClaveAES(socketServidor, buffer);

            // Enviar clave AES a clientes (cifrada con la clave pública de cada cliente)
            enviarClaveAESaClientes(socketServidor);

            // Esperar mensaje del agente
            String mensaje = recibirMensajeAgente(socketServidor, buffer);
            System.out.println("Servidor: Mensaje recibido y descifrado -> " + mensaje);

            // Enviar a clientes
            enviarMensajeClientes(socketServidor, mensaje);

            // Esperar ACKs
            esperandoConfirmacionCliente(socketServidor);

            System.out.println("Servidor: Todos los ACKs recibidos. Cerrando servidor.");
            socketServidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== Métodos =====

    public static void registrarClientes(DatagramSocket socket, byte[] buffer) throws IOException {
        while (clientes.size() < 2) { // o la cantidad de clientes que quieras
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete p = (Paquete) deserializar(paquete.getData());

            if (p.getTipo().equals("REGISTER")) {
                SocketAddress remitente = new InetSocketAddress(paquete.getAddress(), paquete.getPort());

                // Obtener la clave pública enviada
                try {
                    PublicKey pubRemitente = KeyFactory.getInstance("RSA")
                            .generatePublic(new java.security.spec.X509EncodedKeySpec(p.getDatos()));
                    System.out.println(p.getOrigen() + " registrado desde " + remitente);

                    // Si es cliente, guardarlo
                    if (p.getOrigen().startsWith("Cliente")) {
                        clientes.add(remitente);
                        ackRecibidos.put(remitente, false);
                        clavesPublicasClientes.put(remitente, pubRemitente);
                        System.out.println("Cliente registrado: " + remitente + " (clave pública guardada).");
                    } else if (p.getOrigen().equals("Agente")) {
                        System.out.println("Agente registrado: " + remitente + " (clave pública recibida).");
                        // Podés guardar su clave si querés verificar firmas más adelante
                    }

                    // Enviar la clave pública del servidor a todos los que se registren (clientes o agente)
                    byte[] pub = parRSA.getPublic().getEncoded();
                    Paquete pubPacket = new Paquete("CLAVE_PUBLICA", pub, null, "Servidor");
                    enviar(socket, pubPacket, remitente);

                } catch (Exception ex) {
                    System.out.println("No se pudo reconstruir la clave pública del remitente: " + ex.getMessage());
                }
            }
        }
    }


    public static void recibirClaveAES(DatagramSocket socket, byte[] buffer) throws Exception {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        if (recibido.getTipo().equals("CLAVE_AES")) {
            byte[] claveDescifrada = Encriptador.descifrarRSA(recibido.getDatos(), parRSA.getPrivate());
            claveAES = Encriptador.bytesASecretKey(claveDescifrada);
            System.out.println("Servidor: Clave AES recibida y descifrada correctamente.");
        }
    }

    public static void enviarClaveAESaClientes(DatagramSocket socket) throws Exception {
        byte[] aesBytes = Encriptador.claveAESaBytes(claveAES);
        for (SocketAddress cliente : clientes) {
            // Obtener la clave pública del cliente
            PublicKey pubCliente = clavesPublicasClientes.get(cliente);
            if (pubCliente == null) {
                System.out.println("No se encontró clave pública para " + cliente + ". No se enviará AES a este cliente.");
                continue;
            }

            // Encriptar AES con la clave pública del cliente
            byte[] aesCifrada = Encriptador.cifrarRSA(aesBytes, pubCliente);
            Paquete aesPacket = new Paquete("CLAVE_AES", aesCifrada, null, "Servidor");
            enviar(socket, aesPacket, cliente);
        }
        System.out.println("Servidor: Clave AES enviada (cifrada) a todos los clientes que proporcionaron su pública.");
    }

    public static String recibirMensajeAgente(DatagramSocket socket, byte[] buffer) throws Exception {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        byte[] descifrado = Encriptador.descifrarAES(recibido.getDatos(), claveAES, recibido.getIv());
        return new String(descifrado);
    }

    public static void enviarMensajeClientes(DatagramSocket socket, String mensaje) throws Exception {
        for (SocketAddress cliente : clientes) {
            byte[] iv = Encriptador.generarIV();
            byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
            Paquete p = new Paquete("MENSAJE", cifrado, iv, "Servidor");
            enviar(socket, p, cliente);
            System.out.println("Servidor: Enviado mensaje cifrado a " + cliente);
        }
    }

    public static void esperandoConfirmacionCliente(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[65535];
        while (ackRecibidos.containsValue(false)) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete ack = (Paquete) deserializar(paquete.getData());
            if (ack.getTipo().equals("ACK")) {
                ackRecibidos.put(paquete.getSocketAddress(), true);
                System.out.println("Servidor: ACK recibido de " + paquete.getSocketAddress());
            }
        }
    }

    // ===== Auxiliares =====

    public static void enviar(DatagramSocket socket, Paquete p, SocketAddress destino) throws IOException {
        byte[] datos = serializar(p);
        DatagramPacket paquete = new DatagramPacket(datos, datos.length,
                ((InetSocketAddress) destino).getAddress(), ((InetSocketAddress) destino).getPort());
        socket.send(paquete);
    }

    public static byte[] serializar(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.flush();
        return baos.toByteArray();
    }

    public static Object deserializar(byte[] datos) throws IOException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(datos);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new IOException("Error al deserializar", e);
        }
    }
}
