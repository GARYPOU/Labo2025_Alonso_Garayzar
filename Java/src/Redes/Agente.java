package Redes;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Agente {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (Scanner sc = new Scanner(System.in)) {
            DatagramSocket socket = new DatagramSocket();


                System.out.print("Escribe un mensaje: ");
                String mensaje = sc.nextLine();

                byte[] datos = mensaje.getBytes();
                InetAddress direccion = InetAddress.getByName(host);

                DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccion, puerto);
                socket.send(paquete);

                // Recibir respuesta
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRespuesta);

                String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                System.out.println("Servidor responde: " + respuesta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
