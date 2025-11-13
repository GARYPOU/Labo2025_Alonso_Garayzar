package P6;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

public class Server {
    private static final Map<SocketAddress, PublicKey> clavesPublicasClientes = new HashMap<>();
    private static KeyPair parRSA;
    private static SecretKey claveAES;
    private static boolean agenteRegistrado = false;
    private static SocketAddress agenteAddress = null;
    private static Configuracion config;

    public static void main(String[] args) {
        try {
            config = new Configuracion("server.properties");
            int puertoServidor = config.getIntProperty("server.port");
            int clientesEsperados = config.getIntProperty("server.expectedClients");

            DatagramSocket socketServidor = new DatagramSocket(puertoServidor);
            parRSA = Encriptador.generarParRSA(2048);
            byte[] buffer = new byte[65535];

            System.out.println("🚀 Servidor iniciado en puerto: " + puertoServidor);
            System.out.println("📋 Esperando " + clientesEsperados + " clientes y 1 agente...");

            List<SocketAddress> clientes = registrarParticipantes(socketServidor, buffer, clientesEsperados);
            Map<SocketAddress, Boolean> ackRecibidos = new HashMap<>();
            for (SocketAddress cliente : clientes) {
                ackRecibidos.put(cliente, false);
            }

            System.out.println("🔑 Esperando clave AES del agente...");
            recibirClaveAES(socketServidor, buffer);

            if (!clientes.isEmpty()) {
                enviarClaveAESaClientes(socketServidor, clientes);
            } else {
                System.out.println("⚠️  No hay clientes registrados para enviar la clave AES.");
            }

            String mensaje = recibirMensajeAgente(socketServidor, buffer);
            System.out.println("📨 Servidor: Mensaje recibido y descifrado -> " + mensaje);

            if (!clientes.isEmpty()) {
                enviarMensajeClientes(socketServidor, mensaje, clientes);
                esperandoConfirmacionCliente(socketServidor, clientes, ackRecibidos);
            } else {
                System.out.println("⚠️  No hay clientes para enviar el mensaje.");
            }

            System.out.println("✅ Servidor: Proceso completado. Cerrando servidor.");
            socketServidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<SocketAddress> registrarParticipantes(DatagramSocket socket, byte[] buffer, int clientesEsperados) throws IOException {
        List<SocketAddress> clientes = new ArrayList<>();

        while (clientes.size() < clientesEsperados || !agenteRegistrado) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete p = (Paquete) deserializar(paquete.getData());

            if (p.getTipo().equals("REGISTER")) {
                SocketAddress remitente = new InetSocketAddress(paquete.getAddress(), paquete.getPort());

                try {
                    PublicKey pubRemitente = KeyFactory.getInstance("RSA")
                            .generatePublic(new java.security.spec.X509EncodedKeySpec(p.getDatos()));

                    byte[] pub = parRSA.getPublic().getEncoded();
                    Paquete pubPacket = new Paquete("CLAVE_PUBLICA", pub, null, "Servidor");
                    enviar(socket, pubPacket, remitente);

                    if (p.getOrigen().startsWith("Cliente")) {
                        if (!clientes.contains(remitente)) {
                            clientes.add(remitente);
                            clavesPublicasClientes.put(remitente, pubRemitente);
                            System.out.println("✅ Cliente registrado: " + remitente +
                                    " (Total: " + clientes.size() + "/" + clientesEsperados + ")");
                        }
                    } else if (p.getOrigen().equals("Agente")) {
                        if (!agenteRegistrado) {
                            agenteRegistrado = true;
                            agenteAddress = remitente;
                            clavesPublicasClientes.put(remitente, pubRemitente);
                            System.out.println("✅ Agente registrado: " + remitente);
                        }
                    }

                } catch (Exception ex) {
                    System.out.println("❌ Error procesando registro: " + ex.getMessage());
                }
            }

            System.out.println("📊 Estado: " + clientes.size() + "/" + clientesEsperados + " clientes, " +
                    (agenteRegistrado ? "1/1 agente" : "0/1 agente"));

            buffer = new byte[65535];
        }
        System.out.println("🎉 Todos los participantes registrados correctamente.");
        return clientes;
    }

    public static void recibirClaveAES(DatagramSocket socket, byte[] buffer) throws Exception {
        System.out.println("🔑 Esperando clave AES del agente registrado...");

        boolean claveRecibida = false;
        while (!claveRecibida) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete recibido = (Paquete) deserializar(paquete.getData());

            if (recibido.getTipo().equals("CLAVE_AES") && recibido.getOrigen().equals("Agente")) {
                byte[] claveDescifrada = Encriptador.descifrarRSA(recibido.getDatos(), parRSA.getPrivate());
                claveAES = Encriptador.bytesASecretKey(claveDescifrada);
                System.out.println("✅ Servidor: Clave AES recibida y descifrada correctamente del agente.");
                claveRecibida = true;
            } else {
                System.out.println("📦 Paquete recibido no es CLAVE_AES del agente: " + recibido.getTipo() + " de " + recibido.getOrigen());
            }
            buffer = new byte[65535];
        }
    }

    public static void enviarClaveAESaClientes(DatagramSocket socket, List<SocketAddress> clientes) throws Exception {
        System.out.println("📤 Enviando clave AES a " + clientes.size() + " clientes...");

        byte[] aesBytes = Encriptador.claveAESaBytes(claveAES);
        for (SocketAddress cliente : clientes) {
            PublicKey pubCliente = clavesPublicasClientes.get(cliente);
            if (pubCliente != null) {
                byte[] aesCifrada = Encriptador.cifrarRSA(aesBytes, pubCliente);
                Paquete aesPacket = new Paquete("CLAVE_AES", aesCifrada, null, "Servidor");
                enviar(socket, aesPacket, cliente);
                System.out.println("✅ Clave AES enviada a: " + cliente);
            } else {
                System.out.println("❌ No se encontró clave pública para: " + cliente);
            }
        }
    }

    public static String recibirMensajeAgente(DatagramSocket socket, byte[] buffer) throws Exception {
        System.out.println("📨 Esperando mensaje del agente...");

        boolean mensajeRecibido = false;
        while (!mensajeRecibido) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete recibido = (Paquete) deserializar(paquete.getData());

            if (recibido.getTipo().equals("MENSAJE") && recibido.getOrigen().equals("Agente")) {
                PublicKey pubAgente = clavesPublicasClientes.get(agenteAddress);
                boolean firmaValida = Encriptador.verify(recibido.getDatos(), recibido.getFirma(), pubAgente);
                if (!firmaValida) {
                    throw new SecurityException("❌ FIRMA INVALIDA del agente: El mensaje ha sido alterado!");
                }
                System.out.println("✅ Firma del agente verificada: mensaje íntegro");

                byte[] descifrado = Encriptador.descifrarAES(recibido.getDatos(), claveAES, recibido.getIv());
                mensajeRecibido = true;
                buffer = new byte[65535];
                return new String(descifrado);
            }
            buffer = new byte[65535];
        }
        return null;
    }

    public static void enviarMensajeClientes(DatagramSocket socket, String mensaje, List<SocketAddress> clientes) throws Exception {
        System.out.println("📤 Enviando mensaje a " + clientes.size() + " clientes...");

        for (SocketAddress cliente : clientes) {
            byte[] iv = Encriptador.generarIV();
            byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
            byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());
            Paquete p = new Paquete("MENSAJE", cifrado, iv, "Servidor", firma);
            enviar(socket, p, cliente);
            System.out.println("✅ Mensaje firmado y enviado a: " + cliente);
        }
    }

    public static void esperandoConfirmacionCliente(DatagramSocket socket, List<SocketAddress> clientes, Map<SocketAddress, Boolean> ackRecibidos) throws Exception {
        System.out.println("⏳ Esperando ACKs de " + clientes.size() + " clientes...");

        byte[] buffer = new byte[65535];
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
                        System.out.println("✅ ACK firmado válido recibido de " + remitente + " (" + ackRecibidosCount + "/" + ackEsperados + ")");
                    } else {
                        System.out.println("❌ ACK con firma inválida de " + remitente);
                    }
                }
            }
            buffer = new byte[65535];
        }
        System.out.println("🎉 Todos los ACKs válidos recibidos.");
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