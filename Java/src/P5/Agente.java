package P5;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Properties;
import java.util.Scanner;

public class Agente {

    private static SecretKey claveAES;
    private static PublicKey pubServidor;
    private static KeyPair parRSA; // par RSA propio del agente

    public static void main(String[] args) {
        // Si no se pasa argumento, usar un archivo por defecto según la clase
        String configPath;
        if (args.length > 0) {
            configPath = args[0];
        } else {
            // IntelliJ ejecuta desde el directorio raíz del proyecto
            String className = Agente.class.getSimpleName().toLowerCase(); // cambia según la clase
            configPath = className + ".properties";
            System.out.println("⚙️  No se pasó archivo, usando por defecto: " + configPath);
        }


        try {
            Properties props = cargarProperties(configPath);
            String host = props.getProperty("server.host");
            if (host == null) throw new IllegalArgumentException("Falta server.host en el archivo de config.");
            int puerto = Integer.parseInt(props.getProperty("server.port"));

            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName(host);

            // Generar par RSA propio
            parRSA = Encriptador.generarParRSA(2048);

            escribirMensaje(socket, direccionServidor, puerto);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void escribirMensaje(DatagramSocket socket, InetAddress direccionServidor, int puertoServidor) throws Exception {
        registrarAgente(socket, direccionServidor, puertoServidor);
        recibirClavePublica(socket);

        // Generar y enviar clave AES cifrada con la clave pública del servidor
        claveAES = Encriptador.generarClaveAES(128);
        byte[] aesCifrada = Encriptador.cifrarRSA(Encriptador.claveAESaBytes(claveAES), pubServidor);
        Paquete clavePacket = new Paquete("CLAVE_AES", aesCifrada, null, "Agente");
        enviar(socket, clavePacket, direccionServidor, puertoServidor);

        // Escribir mensaje
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe un mensaje: ");
        String mensaje = sc.nextLine();
        byte[] iv = Encriptador.generarIV();
        byte[] cifrado = Encriptador.cifrarAES(mensaje.getBytes(), claveAES, iv);
        Paquete mensajePacket = new Paquete("MENSAJE", cifrado, iv, "Agente");
        enviar(socket, mensajePacket, direccionServidor, puertoServidor);

        System.out.println("Agente: Mensaje cifrado enviado al servidor.");
    }

    public static void registrarAgente(DatagramSocket socket, InetAddress direccionServidor, int puertoServidor) throws IOException {
        // En el REGISTER incluimos la clave pública del agente (encoded)
        byte[] pubAgente = parRSA.getPublic().getEncoded();
        Paquete p = new Paquete("REGISTER", pubAgente, null, "Agente");
        enviar(socket, p, direccionServidor, puertoServidor);
        System.out.println("Agente: Registrado en el servidor (clave pública enviada).");
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
        System.out.println("Agente: Clave pública RSA recibida del servidor.");
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

    private static Properties cargarProperties(String ruta) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(ruta)) {
            props.load(fis);
        }
        return props;
    }
}
