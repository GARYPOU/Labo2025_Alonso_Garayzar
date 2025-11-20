package P9;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Cliente {
    private static SecretKey claveAES;
    private static PublicKey pubServidor;
    private static KeyPair parRSA;
    private static Configuracion config;
    private static volatile boolean procesoCompletado = false;
    private static AtomicBoolean comunicacionHabilitada = new AtomicBoolean(false);
    private static AtomicBoolean clienteActivo = new AtomicBoolean(true);

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            String configFile = "cliente.properties";
            if (args.length > 0) {
                configFile = args[0];
            }

            config = new Configuracion(configFile);
            String serverHost = config.getProperty("server.host");
            int serverPort = config.getIntProperty("server.port");

            parRSA = Encriptador.generarParRSA(2048);

            socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName(serverHost);

            DatagramSocket finalSocket = socket;

            Thread clienteThread = new Thread(() -> {
                try {
                    registrarCliente(direccionServidor, serverPort, finalSocket);
                    recibirClavePublica(finalSocket);
                    recibirClaveAES(finalSocket);
                    recibirMensaje(finalSocket);
                    EnviarACK(direccionServidor, serverPort, finalSocket);


                    comunicacionHabilitada.set(true);
                    System.out.println("=== CONFIRMACIÓN COMPLETADA ===");
                    System.out.println("Comunicación adicional habilitada - Puede enviar mensajes al servidor");

                    mantenerComunicacionActiva(finalSocket, direccionServidor, serverPort);

                    procesoCompletado = true;

                } catch (Exception e) {
                    if (!procesoCompletado) {
                        e.printStackTrace();
                    }
                }
            });

            clienteThread.start();

            Thread hiloMensajes = new Thread(() -> {
                try {
                    while (!comunicacionHabilitada.get()) {
                        Thread.sleep(1000);
                    }

                    enviarMensajesAdicionales(finalSocket, direccionServidor, serverPort);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            hiloMensajes.start();

            clienteThread.join();
            hiloMensajes.join();

            System.out.println("Proceso del cliente completado. Cerrando...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clienteActivo.set(false);
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private static void notificarSalida(DatagramSocket socket, InetAddress dir, int puerto) {
        try {
            byte[] iv = Encriptador.generarIV();
            byte[] mensajeSalida = "CLIENTE_SALIENDO".getBytes();
            byte[] cifrado = Encriptador.cifrarAES(mensajeSalida, claveAES, iv);
            byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());

            Paquete salidaPacket = new Paquete("SALIR", cifrado, iv, "Cliente", firma);
            enviar(socket, salidaPacket, dir, puerto);
            System.out.println("Notificación de salida enviada al servidor");
        } catch (Exception e) {
            System.out.println("Error enviando notificación de salida: " + e.getMessage());
        }
    }

    public static void registrarCliente(InetAddress direccionServidor, int puertoServidor, DatagramSocket socket) throws IOException {
        byte[] pubCliente = parRSA.getPublic().getEncoded();
        Paquete p = new Paquete("REGISTER", pubCliente, null, "Cliente");
        enviar(socket, p, direccionServidor, puertoServidor);
        System.out.println("Registrado en el servidor (clave pública enviada).");
    }

    public static void recibirClavePublica(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[2048];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        if (!"CLAVE_PUBLICA".equals(recibido.getTipo())) {
            throw new IllegalStateException("Se esperaba CLAVE_PUBLICA, llegó: " + recibido.getTipo());
        }
        pubServidor = KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(recibido.getDatos()));
        System.out.println("Clave pública RSA recibida del servidor.");
    }

    public static void recibirClaveAES(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[2048];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        if (!"CLAVE_AES".equals(recibido.getTipo())) {
            throw new IllegalStateException("Se esperaba CLAVE_AES, llegó: " + recibido.getTipo());
        }
        byte[] aesDescifrada = Encriptador.descifrarRSA(recibido.getDatos(), parRSA.getPrivate());
        claveAES = Encriptador.bytesASecretKey(aesDescifrada);
        System.out.println("Clave AES recibida y lista (descifrada con mi privada).");
    }

    public static void recibirMensaje(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[2048];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        if (!"MENSAJE".equals(recibido.getTipo())) {
            throw new IllegalStateException("Se esperaba MENSAJE, llegó: " + recibido.getTipo());
        }

        boolean firmaValida = Encriptador.verify(recibido.getDatos(), recibido.getFirma(), pubServidor);
        if (!firmaValida) {
            throw new SecurityException("FIRMA INVALIDA");
        }
        System.out.println("Firma verificada");

        byte[] descifrado = Encriptador.descifrarAES(recibido.getDatos(), claveAES, recibido.getIv());
        System.out.println("Mensaje descifrado -> " + new String(descifrado));
    }

    public static void EnviarACK(InetAddress dir, int puerto, DatagramSocket socket) throws Exception {
        System.out.print("Ingrese mensaje de confirmacion: ");
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine().trim();
        byte[] iv = Encriptador.generarIV();
        byte[] cifrado = Encriptador.cifrarAES(respuesta.getBytes(), claveAES, iv);
        byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());
        Paquete ack = new Paquete("ACK", cifrado, iv, "Cliente", firma);
        enviar(socket, ack, dir, puerto);
        System.out.println("ACK cifrado y firmado enviado.");
    }

    private static void mantenerComunicacionActiva(DatagramSocket socket, InetAddress dir, int puerto) {
        System.out.println("Escuchando mensajes adicionales del servidor...");

        try {
            byte[] buffer = new byte[2048];

            while (clienteActivo.get() && comunicacionHabilitada.get()) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.setSoTimeout(3000);

                try {
                    socket.receive(paquete);
                    Paquete recibido = (Paquete) deserializar(paquete.getData());
                    procesarMensajeServidor(recibido);

                } catch (java.net.SocketTimeoutException e) {

                }

                buffer = new byte[2048];
            }
        } catch (Exception e) {
            if (clienteActivo.get() && comunicacionHabilitada.get()) {
                e.printStackTrace();
            }
        }
    }

    private static void procesarMensajeServidor(Paquete paquete) {
        try {
            if ("RESPUESTA_SERVIDOR".equals(paquete.getTipo())) {
                boolean firmaValida = Encriptador.verify(paquete.getDatos(), paquete.getFirma(), pubServidor);
                if (firmaValida) {
                    byte[] descifrado = Encriptador.descifrarAES(paquete.getDatos(), claveAES, paquete.getIv());
                    String mensaje = new String(descifrado);
                    System.out.println("Respuesta del servidor: " + mensaje);
                }
            }
        } catch (Exception e) {
            System.out.println("Error procesando mensaje del servidor: " + e.getMessage());
        }
    }

    private static void enviarMensajesAdicionales(DatagramSocket socket, InetAddress dir, int puerto) {
        Scanner scanner = new Scanner(System.in);

        while (clienteActivo.get() && comunicacionHabilitada.get()) {
            try {
                System.out.println("\n--- MENÚ COMUNICACIÓN ADICIONAL ---");
                System.out.println("1. Enviar mensaje al servidor");
                System.out.println("2. Salir");
                System.out.print("Seleccione opción: ");

                String opcion = scanner.nextLine().trim();

                if (opcion.equals("1")) {
                    System.out.print("Escriba su mensaje: ");
                    String mensaje = scanner.nextLine();

                    byte[] iv = Encriptador.generarIV();
                    byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
                    byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());

                    Paquete mensajePacket = new Paquete("MENSAJE_ADICIONAL", cifrado, iv, "Cliente", firma);
                    enviar(socket, mensajePacket, dir, puerto);
                    System.out.println("Mensaje adicional enviado al servidor");
                } else if (opcion.equals("2")) {
                    System.out.println("Saliendo...");
                    notificarSalida(socket, dir, puerto);
                    clienteActivo.set(false);
                    comunicacionHabilitada.set(false);
                    break;
                } else {
                    System.out.println("Opción no válida");
                }

            } catch (Exception e) {
                System.out.println("Error enviando mensaje adicional: " + e.getMessage());
            }
        }
    }

    public static void enviar(DatagramSocket socket, Paquete p, InetAddress dir, int puerto) throws IOException {
        byte[] data = serializar(p);
        DatagramPacket packet = new DatagramPacket(data, data.length, dir, puerto);
        socket.send(packet);
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
