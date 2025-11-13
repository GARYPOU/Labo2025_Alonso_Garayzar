package P6;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Scanner;

public class Agente {

    private static SecretKey claveAES;
    private static PublicKey pubServidor;
    private static KeyPair parRSA;
    private static Configuracion config;

    public static void main(String[] args) {
        try {
            config = new Configuracion("agente.properties");
            String serverHost = config.getProperty("server.host");
            int serverPort = config.getIntProperty("server.port");

            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName(serverHost);

            parRSA = Encriptador.generarParRSA(2048);

            escribirMensaje(socket, direccionServidor, serverPort);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void escribirMensaje(DatagramSocket socket, InetAddress direccionServidor, int puertoServidor) throws Exception {
        registrarAgente(socket, direccionServidor, puertoServidor);
        recibirClavePublica(socket);

        claveAES = Encriptador.generarClaveAES(128);
        byte[] aesCifrada = Encriptador.cifrarRSA(Encriptador.claveAESaBytes(claveAES), pubServidor);
        Paquete clavePacket = new Paquete("CLAVE_AES", aesCifrada, null, "Agente");
        enviar(socket, clavePacket, direccionServidor, puertoServidor);

        Scanner sc = new Scanner(System.in);
        System.out.print("✍️  Escribe un mensaje: ");
        String mensaje = sc.nextLine();
        byte[] iv = Encriptador.generarIV();
        byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
        byte[] firma = Encriptador.sign(cifrado, parRSA.getPrivate());
        Paquete mensajePacket = new Paquete("MENSAJE", cifrado, iv, "Agente", firma);
        enviar(socket, mensajePacket, direccionServidor, puertoServidor);

        System.out.println("✅ Agente: Mensaje cifrado y firmado enviado al servidor.");
    }

    public static void registrarAgente(DatagramSocket socket, InetAddress direccionServidor, int puertoServidor) throws IOException {
        byte[] pubAgente = parRSA.getPublic().getEncoded();
        Paquete p = new Paquete("REGISTER", pubAgente, null, "Agente");
        enviar(socket, p, direccionServidor, puertoServidor);
        System.out.println("📝 Agente: Registrado en el servidor (clave pública enviada).");
    }

    public static void recibirClavePublica(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[65535];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        if (!"CLAVE_PUBLICA".equals(recibido.getTipo())) {
            throw new IllegalStateException("Se esperaba CLAVE_PUBLICA, llegó: " + recibido.getTipo());
        }
        pubServidor = KeyFactory.getInstance("RSA")
                .generatePublic(new java.security.spec.X509EncodedKeySpec(recibido.getDatos()));
        System.out.println("🔑 Agente: Clave pública RSA recibida del servidor.");
    }

    public static void enviar(DatagramSocket socket, Paquete p, InetAddress dir, int puerto) throws IOException {
        byte[] data = serializar(p);
        DatagramPacket packet = new DatagramPacket(data, data.length, dir, puerto);
        socket.send(packet);
    }

    public static byte[] serializar(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.flush();
        return baos.toByteArray();
    }

    public static Object deserializar(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
}