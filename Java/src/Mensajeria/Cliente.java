package Mensajeria;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

public abstract class Cliente {
    private static SecretKey claveAES;
    private static PublicKey pubServidor;
    private static KeyPair parRSA;
    private static Configuracion config;

    public static void main(String[] args) {
        try {
            String configFile = "cliente.properties";
            if (args.length > 0) {
                configFile = args[0];
            }

            config = new Configuracion(configFile);
            String serverHost = config.getProperty("server.host");
            int serverPort = config.getIntProperty("server.port");

            parRSA = Encriptador.generarParRSA(2048);

            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName(serverHost);

            registrarCliente(direccionServidor, serverPort, socket);
            recibirClavePublica(socket);
            recibirClaveAES(socket);
            recibirMensaje(socket);
            EnviarACK(direccionServidor, serverPort, socket);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
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