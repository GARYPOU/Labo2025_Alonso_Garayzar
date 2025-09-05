package Redes;

import java.net.*;
import java.util.Scanner;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class Cliente2{

    public static void main(String[] args) {
        int puertoLocal = 5002;   // puerto donde este cliente escucha
        int puertoDestino = 5000; // puerto del otro cliente
        String hostDestino = "localhost";

        try {
            DatagramSocket socket = new DatagramSocket(puertoLocal);
            InetAddress direccion = InetAddress.getByName(hostDestino);

            Thread receptor = new Thread(() -> {
                byte[] buffer = new byte[1024];
                while (true) {
                    try {
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paquete);
                        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                        System.out.println("\n[Recibido] " + mensaje);


                        String respuesta = "Confirmado Cliente2";
                        byte[] datosRespuesta = respuesta.getBytes();
                        DatagramPacket paqueteRespuesta = new DatagramPacket(datosRespuesta, datosRespuesta.length, direccion, puertoDestino);
                        socket.send(paqueteRespuesta);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            receptor.start();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
