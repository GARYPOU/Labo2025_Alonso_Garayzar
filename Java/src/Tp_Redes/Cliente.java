package Tp_Redes;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            DatagramSocket socket = new DatagramSocket(); // puerto dinámico
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            int puertoServidor = 5000;

            // Paso 1: Registrarse en el servidor
            String registro = "REGISTER";
            DatagramPacket paqueteRegistro = new DatagramPacket(
                    registro.getBytes(),
                    registro.length(),
                    direccionServidor,
                    puertoServidor
            );
            socket.send(paqueteRegistro);
            System.out.println("Cliente: Enviado paquete de registro al servidor.");

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Cliente: Mensaje recibido: " + mensaje);

                System.out.println("Ingrese ACK para confirmar que está activo:");
                String respuesta = scanner.nextLine().trim();
                byte[] datosAck = respuesta.getBytes();

                DatagramPacket ack = new DatagramPacket(datosAck, datosAck.length, direccionServidor, puertoServidor);
                socket.send(ack);
                System.out.println("Cliente: ACK enviado al servidor.");
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
