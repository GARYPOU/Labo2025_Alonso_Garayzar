package O;
import java.net.*;

public class Clientee {
    public static void main(String[] args) {
        int puertoCliente = 6002; // Cambiar a 6002 para el segundo cliente
        try {
            DatagramSocket socket = new DatagramSocket(puertoCliente);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Cliente (" + puertoCliente + "): Mensaje recibido: " + mensaje);

                // Simular una respuesta ACK
                String respuesta = "ACK";
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
