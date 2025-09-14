package Tp_Redes;
import java.net.*;
import java.util.*;


public class Server extends Thread{
    private static final int PUERTO_SERVIDOR = 5000;
    private static final int PUERTO_CLIENTE1 = 6001;
    private static final int PUERTO_CLIENTE2 = 6002;

    private static final HashMap<Integer, Boolean> ackRecibidos = new HashMap<>();

    public static void main(String[] args) {
        try {
            DatagramSocket socketServidor = new DatagramSocket(PUERTO_SERVIDOR);
            byte[] buffer = new byte[1024];


            DatagramPacket paqueteEntrante = new DatagramPacket(buffer, buffer.length);
            socketServidor.receive(paqueteEntrante);
            String mensaje = new String(paqueteEntrante.getData(), 0, paqueteEntrante.getLength());
            System.out.println("Servidor: Mensaje recibido del Agente: " + mensaje);
            ackRecibidos.put(PUERTO_CLIENTE1, false);
            ackRecibidos.put(PUERTO_CLIENTE2, false);
            new Thread(() -> manejarCliente(socketServidor, mensaje, PUERTO_CLIENTE1)).start();
            new Thread(() -> manejarCliente(socketServidor, mensaje, PUERTO_CLIENTE2)).start();
            while (!ackRecibidos.get(PUERTO_CLIENTE1) || !ackRecibidos.get(PUERTO_CLIENTE2)) {
                DatagramPacket respuesta = new DatagramPacket(new byte[1024], 1024);
                socketServidor.receive(respuesta);
                String ack = new String(respuesta.getData(), 0, respuesta.getLength());
                int puertoCliente = respuesta.getPort();
                if (ack.equalsIgnoreCase("ACK")) {
                    ackRecibidos.put(puertoCliente, true);
                    System.out.println("Servidor: ACK recibido del cliente en puerto " + puertoCliente);
                }
            }

            System.out.println("Servidor: Todos los ACKs recibidos. Cerrando servidor.");
            socketServidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void manejarCliente(DatagramSocket socket, String mensaje, int puertoCliente) {
        try {
            InetAddress direccion = InetAddress.getByName("localhost");
            byte[] datos = mensaje.getBytes();

            while (!ackRecibidos.get(puertoCliente)) {
                DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccion, puertoCliente);
                socket.send(paquete);
                System.out.println("Servidor: Mensaje enviado a Cliente en puerto " + puertoCliente);
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}