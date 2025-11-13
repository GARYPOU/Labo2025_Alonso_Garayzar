package P5;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Properties;
import java.util.Scanner;

public abstract class Cliente {
    private static SecretKey claveAES;
    private static PublicKey pubServidor;
    private static KeyPair parRSA; // par RSA del cliente

    public static void main(String[] args) {
            try {
                // Detectar archivo de configuración
                String configPath;

                if (args.length > 0) {
                    configPath = args[0];
                } else {
                    // Detectar el nombre de la subclase que lo ejecuta
                    String caller = new Throwable().getStackTrace()[1].getClassName();
                    String simpleName = caller.substring(caller.lastIndexOf('.') + 1).toLowerCase();
                    configPath = simpleName + ".properties"; // ej: cliente1.properties
                    System.out.println("⚙️  Usando configuración por defecto: " + configPath);
                }

                Properties props = cargarProperties(configPath);
                String host = props.getProperty("server.host");
                if (host == null) throw new IllegalArgumentException("Falta server.host en el archivo de config.");
                int puertoServidor = Integer.parseInt(props.getProperty("server.port"));

                // Generar par RSA del cliente
                parRSA = Encriptador.generarParRSA(2048);

                DatagramSocket socket = new DatagramSocket();
                InetAddress direccionServidor = InetAddress.getByName(host);

                registrarCliente(direccionServidor, puertoServidor, socket);
                recibirClavePublica(socket);
                recibirClaveAES(socket);
                recibirMensaje(socket);
                ingresarYEnviarACK(direccionServidor, puertoServidor, socket);

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public static void registrarCliente(InetAddress direccionServidor, int puertoServidor, DatagramSocket socket) throws IOException {
        // Enviar la clave pública del cliente en el REGISTER
        byte[] pubCliente = parRSA.getPublic().getEncoded();
        Paquete p = new Paquete("REGISTER", pubCliente, null, "Cliente");
        enviar(socket, p, direccionServidor, puertoServidor);
        System.out.println("Cliente: Registrado en el servidor (clave pública enviada).");
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
        System.out.println("Cliente: Clave pública RSA recibida del servidor.");
    }

    public static void recibirClaveAES(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[65535];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        if (!"CLAVE_AES".equals(recibido.getTipo())) {
            throw new IllegalStateException("Se esperaba CLAVE_AES, llegó: " + recibido.getTipo());
        }
        // Ahora sí usamos la clave privada local del cliente para descifrar
        byte[] aesDescifrada = Encriptador.descifrarRSA(recibido.getDatos(), parRSA.getPrivate());
        claveAES = Encriptador.bytesASecretKey(aesDescifrada);
        System.out.println("Cliente: Clave AES recibida y lista (descifrada con mi privada).");
    }

    public static void recibirMensaje(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[65535];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        Paquete recibido = (Paquete) deserializar(paquete.getData());
        if (!"MENSAJE".equals(recibido.getTipo())) {
            throw new IllegalStateException("Se esperaba MENSAJE, llegó: " + recibido.getTipo());
        }
        byte[] descifrado = Encriptador.descifrarAES(recibido.getDatos(), claveAES, recibido.getIv());
        System.out.println("Cliente: Mensaje descifrado -> " + new String(descifrado));
    }

    public static void ingresarYEnviarACK(InetAddress dir, int puerto, DatagramSocket socket) throws Exception {
        System.out.print("Ingrese 'ACK' para confirmar: ");
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine().trim();
        byte[] iv = Encriptador.generarIV();
        byte[] cifrado = Encriptador.cifrarAES(respuesta.getBytes(), claveAES, iv);
        Paquete ack = new Paquete("ACK", cifrado, iv, "Cliente");
        enviar(socket, ack, dir, puerto);
        System.out.println("Cliente: ACK cifrado enviado.");
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
