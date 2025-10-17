package P1;
import java.net.*;
import java.util.Scanner;

public class Agente {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.print("Escribe un mensaje: ");
            String mensaje = sc.nextLine();
            byte[] buffer = mensaje.getBytes();
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccionServidor, 5000);
            socket.send(paquete);
            System.out.println("Agente: Mensaje enviado al servidor.");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
