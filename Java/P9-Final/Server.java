
package P9;

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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final ConcurrentHashMap<SocketAddress, PublicKey> clavesPublicasClientes = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<SocketAddress, Boolean> clientesConfirmados = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<SocketAddress, Boolean> clientesSalieron = new ConcurrentHashMap<>();
    private static KeyPair parRSA;
    private static SecretKey claveAES;
    private static boolean agenteRegistrado = false;
    private static SocketAddress agenteAddress = null;
    private static Configuracion config;
    private static ExecutorService executor = Executors.newCachedThreadPool();
    private static volatile boolean servidorActivo = true;
    private static volatile boolean comunicacionHabilitada = false;
    private static int clientesEsperados = 0;
    private static int clientesQueSalieron = 0;

    public static void main(String[] args) {
        try {
            config = new Configuracion(args[0]);
            int puertoServidor = config.getIntProperty("server.port");
            clientesEsperados = config.getIntProperty("server.expectedClients");

            DatagramSocket socketServidor = new DatagramSocket(puertoServidor);
            parRSA = Encriptador.generarParRSA(2048);
            byte[] buffer = new byte[2048];

            System.out.println("Servidor iniciado en puerto: " + puertoServidor);
            System.out.println("Esperando " + clientesEsperados + " clientes y 1 agente...");

            ArrayList<SocketAddress> clientes = registrarParticipantes(socketServidor, buffer, clientesEsperados);

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
                esperarConfirmacionClientes(socketServidor, clientes);
            } else {
                System.out.println("No hay clientes para enviar el mensaje.");
            }


            for (SocketAddress cliente : clientes) {
                clientesSalieron.put(cliente, false);
            }
            comunicacionHabilitada = true;
            System.out.println("=== TODOS LOS CLIENTES CONFIRMADOS ===");
            System.out.println("Comunicación adicional habilitada - Servidor listo para recibir/enviar mensajes cifrados");


            mantenerComunicacionActiva(socketServidor, clientes);

            System.out.println("Proceso completado. Cerrando servidor.");
            servidorActivo = false;
            executor.shutdown();
            socketServidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<SocketAddress> registrarParticipantes(DatagramSocket socket, byte[] buffer, int clientesEsperados) throws IOException, ClassNotFoundException {
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

    private static void procesarSolicitudRegistro(DatagramSocket socket, Paquete paquete, DatagramPacket paqueteRecibido, ArrayList<SocketAddress> clientes, int clientesEsperados) throws IOException {
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

    private static void registrarParticipante(Paquete paquete, SocketAddress remitente, ArrayList<SocketAddress> clientes, int clientesEsperados) {
        if (paquete.getOrigen().startsWith("Cliente")) {
            if (!clientes.contains(remitente)) {
                clientes.add(remitente);
                clientesConfirmados.put(remitente, false);
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
            executor.execute(() -> {
                try {
                    PublicKey pubCliente = clavesPublicasClientes.get(cliente);
                    if (pubCliente != null) {
                        byte[] aesCifrada = Encriptador.cifrarRSA(aesBytes, pubCliente);
                        Paquete aesPacket = new Paquete("CLAVE_AES", aesCifrada, null, "Servidor");
                        enviar(socket, aesPacket, cliente);
                        System.out.println("Clave AES enviada a: " + cliente);
                    } else {
                        System.out.println("No se encontró clave pública para: " + cliente);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
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
            executor.execute(() -> {
                try {
                    byte[] iv = Encriptador.generarIV();
                    byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
                    byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());
                    Paquete p = new Paquete("MENSAJE", cifrado, iv, "Servidor", firma);
                    enviar(socket, p, cliente);
                    System.out.println("Mensaje firmado y enviado a: " + cliente);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void esperarConfirmacionClientes(DatagramSocket socket, ArrayList<SocketAddress> clientes) throws Exception {
        System.out.println("Esperando ACKs de " + clientes.size() + " clientes...");

        CountDownLatch latch = new CountDownLatch(clientes.size());
        byte[] buffer = new byte[2048];
        int ackEsperados = clientes.size();

        while (latch.getCount() > 0) {
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            Paquete ack = (Paquete) deserializar(paquete.getData());

            if (ack.getTipo().equals("ACK")) {
                SocketAddress remitente = paquete.getSocketAddress();
                if (clientes.contains(remitente)) {
                    PublicKey pubCliente = clavesPublicasClientes.get(remitente);
                    boolean firmaValida = Encriptador.verify(ack.getDatos(), ack.getFirma(), pubCliente);

                    if (firmaValida) {
                        clientesConfirmados.put(remitente, true);
                        latch.countDown();
                        System.out.println("ACK firmado válido recibido de " + remitente + " (" + (ackEsperados - latch.getCount()) + "/" + ackEsperados + ")");
                    } else {
                        System.out.println("ACK con firma inválida de " + remitente);
                    }
                }
            }
            buffer = new byte[2048];
        }
        System.out.println("Todos los ACKs válidos recibidos.");
    }

    private static void mantenerComunicacionActiva(DatagramSocket socket, ArrayList<SocketAddress> clientes) {
        System.out.println("Manteniendo comunicación activa para mensajes adicionales...");

        try {
            byte[] buffer = new byte[2048];

            while (servidorActivo) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.setSoTimeout(5000);

                try {
                    socket.receive(paquete);
                    Paquete recibido = (Paquete) deserializar(paquete.getData());
                    SocketAddress remitente = paquete.getSocketAddress();

                    if (clientes.contains(remitente) && comunicacionHabilitada) {
                        procesarMensajeAdicional(recibido, remitente, socket, clientes);
                    }

                } catch (java.net.SocketTimeoutException e) {

                }

                buffer = new byte[2048];
            }
        } catch (Exception e) {
            if (servidorActivo) {
                e.printStackTrace();
            }
        }
    }

    private static void procesarMensajeAdicional(Paquete paquete, SocketAddress remitente, DatagramSocket socket, ArrayList<SocketAddress> clientes) {
        try {
            if ("MENSAJE_ADICIONAL".equals(paquete.getTipo())) {
                PublicKey pubCliente = clavesPublicasClientes.get(remitente);
                boolean firmaValida = Encriptador.verify(paquete.getDatos(), paquete.getFirma(), pubCliente);

                if (firmaValida) {
                    byte[] descifrado = Encriptador.descifrarAES(paquete.getDatos(), claveAES, paquete.getIv());
                    String mensaje = new String(descifrado);
                    System.out.println("Mensaje adicional de " + remitente + ": " + mensaje);


                    enviarRespuestaMensaje(socket, remitente, "Mensaje recibido: " + mensaje);
                } else {
                    System.out.println("Firma inválida en mensaje adicional de: " + remitente);
                }
            } else if ("SALIR".equals(paquete.getTipo())) {
                procesarSalidaCliente(remitente, socket, clientes);
            }
        } catch (Exception e) {
            System.out.println("Error procesando mensaje adicional de " + remitente + ": " + e.getMessage());
        }
    }

    private static void procesarSalidaCliente(SocketAddress remitente, DatagramSocket socket, ArrayList<SocketAddress> clientes) {
        if (!clientesSalieron.get(remitente)) {
            clientesSalieron.put(remitente, true);
            clientesQueSalieron++;

            System.out.println("=== CLIENTE SALIÓ ===");
            System.out.println("Cliente " + remitente + " ha salido explícitamente");
            System.out.println("Clientes que han salido: " + clientesQueSalieron + "/" + clientesEsperados);

            if (clientesQueSalieron == clientesEsperados) {
                System.out.println("=== TODOS LOS CLIENTES HAN SALIDO ===");
                System.out.println("Cerrando servidor...");
                servidorActivo = false;
            }
        }
    }

    private static void enviarRespuestaMensaje(DatagramSocket socket, SocketAddress destino, String mensaje) {
        try {
            byte[] iv = Encriptador.generarIV();
            byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
            byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());

            Paquete respuesta = new Paquete("RESPUESTA_SERVIDOR", cifrado, iv, "Servidor", firma);
            enviar(socket, respuesta, destino);
            System.out.println("Respuesta enviada a: " + destino);
        } catch (Exception e) {
            System.out.println("Error enviando respuesta a " + destino + ": " + e.getMessage());
        }
    }

    public static void enviar(DatagramSocket socket, Paquete p, SocketAddress destino) throws IOException {
        byte[] datos = serializar(p);
        DatagramPacket paquete = new DatagramPacket(datos, datos.length,
                ((InetSocketAddress) destino).getAddress(), ((InetSocketAddress) destino).getPort());
        socket.send(paquete);
    }

    public static byte[] serializar(Object objeto) throws IOException {
        ByteArrayOutputStream contenedorBytes = new ByteArrayOutputStream();
        ObjectOutputStream convertidorABytes = new ObjectOutputStream(contenedorBytes);
        convertidorABytes.writeObject(objeto);
        convertidorABytes.flush();
        return contenedorBytes.toByteArray();
    }

    public static Object deserializar(byte[] datos) throws IOException, ClassNotFoundException {
        ByteArrayInputStream lectorDeBytes = new ByteArrayInputStream(datos);
        ObjectInputStream reconstructorDeObjetos = new ObjectInputStream(lectorDeBytes);
        return reconstructorDeObjetos.readObject();
    }
}
