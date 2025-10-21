package P2;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente2 extends Cliente{


    public static void registrarCliete(InetAddress direccionServidor, int puertoServidor, DatagramSocket socket) throws IOException {
        String registro = "REGISTER";
        DatagramPacket paqueteRegistro = new DatagramPacket(
                registro.getBytes(),
                registro.length(),
                direccionServidor,
                puertoServidor
        );
        socket.send(paqueteRegistro);
        System.out.println("Cliente: Enviado paquete de registro al servidor.");
    }
    public static void recibirMensaje(DatagramSocket socket, byte[] buffer) throws IOException {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
        System.out.println("Cliente: Mensaje recibido: " + mensaje);
    }

    public static String ingresarRespuesta(){
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().trim();

        return  respuesta;
    }

    public static void paqueteRespuesta(byte[] datosAck, InetAddress direccionServidor, int puertoServidor, DatagramSocket socket) throws IOException {
        DatagramPacket ack = new DatagramPacket(datosAck, datosAck.length, direccionServidor, puertoServidor);
        socket.send(ack);
        System.out.println("Cliente: ACK enviado al servidor.");
    }

    public static void main(String[] args) {


        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            int puertoServidor = 5000;
            registrarCliete(direccionServidor,puertoServidor,socket);
            byte[] buffer = new byte[1024];
            boolean verdad=true;
            while (verdad) {
                recibirMensaje(socket,buffer);
                System.out.println("Ingrese ACK para confirmar que está activo:");
                String respuesta=ingresarRespuesta();
                byte[] datosAck = respuesta.getBytes();
                paqueteRespuesta(datosAck,direccionServidor,puertoServidor,socket);
                if(respuesta!="ACK") {
                    verdad=false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}