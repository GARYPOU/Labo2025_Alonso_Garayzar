package Tp_Redes;
import java.net.*;
import java.util.Scanner;
public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int puertoCliente = 6001;
        try {
            DatagramSocket socket = new DatagramSocket(puertoCliente);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Cliente (" + puertoCliente + "): Mensaje recibido: " + mensaje);
                System.out.println("Ingrese ACK para confirmar que esta activo");
                String respuesta = scanner.nextLine();;
                byte[] datosAck = respuesta.getBytes();
                InetAddress direccionServidor = paquete.getAddress();
                int puertoServidor = paquete.getPort();
                DatagramPacket ack = new DatagramPacket(datosAck, datosAck.length, direccionServidor, puertoServidor);
                socket.send(ack);
                System.out.println("Cliente (" + puertoCliente + "): ACK enviado al servidor.");
                break;
            }
            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Cliente (" + puertoCliente + "): Mensaje recibido: " + mensaje);
                System.out.println("Ingrese ACK para confirmar que esta activo");
                String respuesta = scanner.nextLine();
                byte[] datosAck = respuesta.getBytes();
                InetAddress direccionServidor = paquete.getAddress();
                int puertoServidor = paquete.getPort();
                DatagramPacket ack = new DatagramPacket(datosAck, datosAck.length, direccionServidor, puertoServidor);
                socket.send(ack);
                System.out.println("Cliente (" + puertoCliente + "): ACK enviado al servidor.");
                break;
            }
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}