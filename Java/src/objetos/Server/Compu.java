package objetos.Server;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class Compu {
    public static void main(String[] args) {
        try{
            String IP_server = "localhost";
            InetAddress hostServidor = InetAddress.getByName(IP_server);
            int puertinServer=10115;
            byte[] mensaje = new String("Mensaje del cliente").getBytes();
            DatagramSocket socketUPD = new DatagramSocket();
            DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length,hostServidor,puertinServer);
            socketUPD.send(peticion);

            for (int i = 0; i < 10000; i++) {
                byte[] buffer =new byte[1000];
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
                socketUPD.receive(respuesta);
                System.out.println("Repuesta"+ new String(respuesta.getData()));
            }
            socketUPD.close();
        }
        catch (SocketException e){
            System.out.println("Socket:"+e.getMessage());
        }
        catch (IOException e){
            System.out.println("IO:"+e.getMessage());
        }
    }
}
