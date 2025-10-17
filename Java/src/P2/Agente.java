package P2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Agente {

    public static void enviarMensajeyCerrarServidor(byte[] buffer,InetAddress direccionServidor,DatagramSocket socket) throws IOException {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccionServidor, 5000);
        socket.send(paquete);
        System.out.println("Agente: Mensaje enviado al servidor.");
        socket.close();
    }

    public static void escribirMensaje() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        System.out.print("Escribe un mensaje: ");
        String mensaje = sc.nextLine();
        byte[] buffer = mensaje.getBytes();
        InetAddress direccionServidor = InetAddress.getByName("localhost");
        enviarMensajeyCerrarServidor(buffer,direccionServidor,socket);
    }

    public static void main(String[] args) {
        try {
            escribirMensaje();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
