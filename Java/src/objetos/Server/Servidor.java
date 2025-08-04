package objetos.Server;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class Servidor {
    public static void main(String[] args) {
        try{
            int puerto = 10115;
            DatagramSocket socket = new DatagramSocket(puerto);
            byte[] mensaje= new byte[1000];
            DatagramPacket packet = new DatagramPacket(mensaje, mensaje.length);
            socket.receive(packet);
            System.out.println("Direccion:"+packet.getAddress());
            System.out.println("Respuesta"+new String(packet.getData()));
            for (int i = 0; i < 10000; i++) {
                byte[] mensaje_enviar = new String("Este es mi mensaje numero:"+i).getBytes();

                DatagramPacket enviar = new DatagramPacket(mensaje_enviar, mensaje_enviar.length, packet.getAddress(), packet.getPort());
                socket.send(enviar);
            }
            socket.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
