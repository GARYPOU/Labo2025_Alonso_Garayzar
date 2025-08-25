package Redes;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) throws UnknownHostException {
        int puerto = 5000;
        int puertoClie2 = 5002;
        int puertoClie1 = 5001;
        byte[] buffer = new byte[1024];
        HashMap<InetAddress,Integer> Direcciones = new HashMap<>();
        InetAddress direccionServer = InetAddress.getByName("localHost");
        InetAddress direccionClie1 = InetAddress.getByName("localHost");
        InetAddress direccionClie2 = InetAddress.getByName("localHost");

        Direcciones.put(direccionClie1,puertoClie1);
        Direcciones.put(direccionClie2,puertoClie2);

        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Servidor UDP escuchando en el puerto " + puerto);



                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);


                socket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Agente dice: " + mensaje);

            while (true) {
                String respuesta = mensaje;
                byte[] datosRespuesta = respuesta.getBytes();
                for (Map.Entry<InetAddress,Integer> d : Direcciones.entrySet()){

                    DatagramPacket paqueteRespuesta = new DatagramPacket(
                            datosRespuesta, datosRespuesta.length, d.getKey(), d.getValue()
                    );
                    Thread.sleep(5000);

                    socket.send(paqueteRespuesta);



                    socket.receive(paquete);

                    String mensaje2 = new String(paquete.getData(), 0, paquete.getLength());
                    System.out.println("Mensaje: " + mensaje2);
                    Direcciones.remove(paquete.getAddress());



                    }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
