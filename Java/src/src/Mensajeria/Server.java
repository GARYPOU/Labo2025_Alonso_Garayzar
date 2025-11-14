package Mensajeria;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final HashMap<SocketAddress, PublicKey> clavesPublicasClientes = new HashMap<>();
    private static KeyPair parRSA;
    private static SecretKey claveAES;
    private static boolean agenteRegistrado = false;
    private static SocketAddress agenteAddress = null;
    private static Configuracion config;

    public static void main(String[] args) {
        try {
            config = new Configuracion("Server.properties");
            int puertoServidor = config.getIntProperty("server.port");
            int clientesEsperados = config.getIntProperty("server.expectedClients");

            DatagramSocket socketServidor = new DatagramSocket(puertoServidor);
            parRSA = Encriptador.generarParRSA(2048);
            byte[] buffer = new byte[2048];

            System.out.println("Servidor iniciado en puerto: " + puertoServidor);
            System.out.println("Esperando " + clientesEsperados + " clientes y 1 agente...");

            ArrayList <SocketAddress> clientes = registrarParticipantes(socketServidor, buffer, clientesEsperados);
            HashMap<SocketAddress, Boolean> ackRecibidos = new HashMap<>();
            for (SocketAddress cliente : clientes) {
                ackRecibidos.put(cliente, false);
            }

            System.out.println("Esperando clave AES del agente...");
            recibirClaveAES(socketServidor, buffer);

            if (!clientes.isEmpty()) {
                enviarClaveAESaClientes(socketServidor, clientes);
            } else {
                System.out.println("No hay clientes registrados para enviar la clave AES.");
            }

            String mensaje = recibirMensajeAgente(socketServidor, buffer);
            System.out.println("Mensaje recibido y descifrado -> " + mensaje);

            if (!clientes.isEmpty()) {
                enviarMensajeClientes(socketServidor, mensaje, clientes);
                esperandoConfirmacionCliente(socketServidor, clientes, ackRecibidos);
            } else {
                System.out.println("No hay clientes para enviar el mensaje.");
            }

            System.out.println("Proceso completado. Cerrando servidor.");
            socketServidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SocketAddress> registrarParticipantes(DatagramSocket socket, byte[] buffer, int clientesEsperados) throws IOException {
        ArrayList<SocketAddress> clientes = new ArrayList<>();

        while (clientes.size() < clientesEsperados || !agenteRegistrado) {
            DatagramPacket paqueteRecibido = recibirPaquete(socket, buffer);
            Paquete paquete = (Paquete) deserializar(paqueteRecibido.getData());

            if (paquete.getTipo().equals("REGISTER")) {
                procesarSolicitudRegistro(socket, paquete, paqueteRecibido, clientes, clientesEsperados);
            }
            mostrarEstadoActual(clientes.size(), clientesEsperados);
            buffer = new byte[2048];
        }

        System.out.println("Todos los participantes registrados correctamente.");
        return clientes;
    }

    private static DatagramPacket recibirPaquete(DatagramSocket socket, byte[] buffer) throws IOException {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        return paquete;
    }

    private static void procesarSolicitudRegistro(DatagramSocket socket, Paquete paquete, DatagramPacket paqueteRecibido,
                                                  ArrayList<SocketAddress> clientes, int clientesEsperados) throws IOException {
        SocketAddress remitente = new InetSocketAddress(paqueteRecibido.getAddress(), paqueteRecibido.getPort());

        try {
            intercambiarClavesPublicas(socket, paquete, remitente);
            registrarParticipante(paquete, remitente, clientes, clientesEsperados);

        } catch (Exception ex) {
            System.out.println("Error procesando registro: " + ex.getMessage());
        }
    }

    private static void intercambiarClavesPublicas(DatagramSocket socket, Paquete paquete, SocketAddress remitente) throws Exception {

        PublicKey clavePublicaRemitente = KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(paquete.getDatos()));
        byte[] clavePublicaServidor = parRSA.getPublic().getEncoded();
        Paquete paqueteClave = new Paquete("CLAVE_PUBLICA", clavePublicaServidor, null, "Servidor");
        enviar(socket, paqueteClave, remitente);
        clavesPublicasClientes.put(remitente, clavePublicaRemitente);
    }

    private static void registrarParticipante(Paquete paquete, SocketAddress remitente,
                                              ArrayList<SocketAddress> clientes, int clientesEsperados) {
        if (paquete.getOrigen().startsWith("Cliente")) {
            if (!clientes.contains(remitente)) {
                clientes.add(remitente);
                System.out.println("Cliente registrado: " + remitente +
                        " (Total: " + clientes.size() + "/" + clientesEsperados + ")");
            }
        } else if (paquete.getOrigen().equals("Agente")) {
            if (!agenteRegistrado) {
                agenteRegistrado = true;
                agenteAddress = remitente;
                System.out.println("Agente registrado: " + remitente);
            }
        }
    }

    private static void mostrarEstadoActual(int clientesActuales, int clientesEsperados) {
        System.out.println("Estado: " + clientesActuales + "/" + clientesEsperados + " clientes, " +
                (agenteRegistrado ? "1/1 agente" : "0/1 agente"));
    }

    public static void recibirClaveAES(DatagramSocket socket, byte[] buffer) throws Exception {
        System.out.println("Esperando clave AES del agente registrado...");

        boolean claveRecibida = false;
        while (!claveRecibida) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete recibido = (Paquete) deserializar(paquete.getData());

            if (recibido.getTipo().equals("CLAVE_AES") && recibido.getOrigen().equals("Agente")) {
                byte[] claveDescifrada = Encriptador.descifrarRSA(recibido.getDatos(), parRSA.getPrivate());
                claveAES = Encriptador.bytesASecretKey(claveDescifrada);
                System.out.println("Clave AES recibida y descifrada correctamente del agente.");
                claveRecibida = true;
            } else {
                System.out.println("Paquete recibido no es CLAVE_AES del agente: " + recibido.getTipo() + " de " + recibido.getOrigen());
            }
            buffer = new byte[2048];
        }
    }

    public static void enviarClaveAESaClientes(DatagramSocket socket, ArrayList<SocketAddress> clientes) throws Exception {
        System.out.println("Enviando clave AES a " + clientes.size() + " clientes...");

        byte[] aesBytes = Encriptador.claveAESaBytes(claveAES);
        for (SocketAddress cliente : clientes) {
            PublicKey pubCliente = clavesPublicasClientes.get(cliente);
            if (pubCliente != null) {
                byte[] aesCifrada = Encriptador.cifrarRSA(aesBytes, pubCliente);
                Paquete aesPacket = new Paquete("CLAVE_AES", aesCifrada, null, "Servidor");
                enviar(socket, aesPacket, cliente);
                System.out.println("Clave AES enviada a: " + cliente);
            } else {
                System.out.println("No se encontró clave pública para: " + cliente);
            }
        }
    }

    public static String recibirMensajeAgente(DatagramSocket socket, byte[] buffer) throws Exception {
        System.out.println("Esperando mensaje del agente...");

        boolean mensajeRecibido = false;
        while (!mensajeRecibido) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete recibido = (Paquete) deserializar(paquete.getData());

            if (recibido.getTipo().equals("MENSAJE") && recibido.getOrigen().equals("Agente")) {
                PublicKey pubAgente = clavesPublicasClientes.get(agenteAddress);
                boolean firmaValida = Encriptador.verify(recibido.getDatos(), recibido.getFirma(), pubAgente);
                if (!firmaValida) {
                    throw new SecurityException("FIRMA INVALIDA del agente");
                }
                System.out.println("Firma del agente verificada");

                byte[] descifrado = Encriptador.descifrarAES(recibido.getDatos(), claveAES, recibido.getIv());
                mensajeRecibido = true;
                buffer = new byte[2048];
                return new String(descifrado);
            }
            buffer = new byte[2048];
        }
        return null;
    }

    public static void enviarMensajeClientes(DatagramSocket socket, String mensaje, ArrayList<SocketAddress> clientes) throws Exception {
        System.out.println("Enviando mensaje a " + clientes.size() + " clientes...");

        for (SocketAddress cliente : clientes) {
            byte[] iv = Encriptador.generarIV();
            byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
            byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());
            Paquete p = new Paquete("MENSAJE", cifrado, iv, "Servidor", firma);
            enviar(socket, p, cliente);
            System.out.println("Mensaje firmado y enviado a: " + cliente);
        }
    }

    public static void esperandoConfirmacionCliente(DatagramSocket socket, ArrayList<SocketAddress> clientes, Map<SocketAddress, Boolean> ackRecibidos) throws Exception {
        System.out.println("Esperando ACKs de " + clientes.size() + " clientes...");

        byte[] buffer = new byte[2048];
        int ackEsperados = clientes.size();
        int ackRecibidosCount = 0;

        while (ackRecibidosCount < ackEsperados) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete ack = (Paquete) deserializar(paquete.getData());

            if (ack.getTipo().equals("ACK")) {
                SocketAddress remitente = paquete.getSocketAddress();
                if (clientes.contains(remitente) && !ackRecibidos.get(remitente)) {
                    PublicKey pubCliente = clavesPublicasClientes.get(remitente);
                    boolean firmaValida = Encriptador.verify(ack.getDatos(), ack.getFirma(), pubCliente);

                    if (firmaValida) {
                        ackRecibidos.put(remitente, true);
                        ackRecibidosCount++;
                        System.out.println("ACK firmado válido recibido de " + remitente + " (" + ackRecibidosCount + "/" + ackEsperados + ")");
                    } else {
                        System.out.println("ACK con firma inválida de " + remitente);
                    }
                }
            }
            buffer = new byte[2048];
        }
        System.out.println("Todos los ACKs válidos recibidos.");
    }

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