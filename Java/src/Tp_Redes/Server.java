package Tp_Redes;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PUERTO_SERVIDOR = 5000;

    // Lista de clientes registrados (InetAddress + puerto)
    private static final List<SocketAddress> clientes = new ArrayList<>();
    private static final Map<SocketAddress, Boolean> ackRecibidos = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            DatagramSocket socketServidor = new DatagramSocket(PUERTO_SERVIDOR);
            byte[] buffer = new byte[1024];

            System.out.println("Servidor esperando registros de clientes...");

            // Esperamos que al menos 2 clientes se registren (puede ser configurable)
            while (clientes.size() < 2) {
                DatagramPacket registro = new DatagramPacket(buffer, buffer.length);
                socketServidor.receive(registro);
                String mensaje = new String(registro.getData(), 0, registro.getLength()).trim();

                if (mensaje.equalsIgnoreCase("REGISTER")) {
                    SocketAddress clienteAddr = new InetSocketAddress(registro.getAddress(), registro.getPort());
                    if (!clientes.contains(clienteAddr)) {
                        clientes.add(clienteAddr);
                        ackRecibidos.put(clienteAddr, false);
                        System.out.println("Servidor: Cliente registrado -> " + clienteAddr);
                    }
                }
            }

            // Ahora esperamos mensaje del agente
            DatagramPacket paqueteEntrante = new DatagramPacket(buffer, buffer.length);
            socketServidor.receive(paqueteEntrante);
            String mensajeAgente = new String(paqueteEntrante.getData(), 0, paqueteEntrante.getLength());
            System.out.println("Servidor: Mensaje recibido del Agente: " + mensajeAgente);

            // Enviamos a todos los clientes registrados
            for (SocketAddress cliente : clientes) {
                new Thread(() -> manejarCliente(socketServidor, mensajeAgente, cliente)).start();
            }

            // Esperamos ACKs
            while (ackRecibidos.values().contains(false)) {
                DatagramPacket respuesta = new DatagramPacket(new byte[1024], 1024);
                socketServidor.receive(respuesta);
                String ack = new String(respuesta.getData(), 0, respuesta.getLength()).trim();
                SocketAddress remitente = new InetSocketAddress(respuesta.getAddress(), respuesta.getPort());

                if (ack.equalsIgnoreCase("ACK") && ackRecibidos.containsKey(remitente)) {
                    ackRecibidos.put(remitente, true);
                    System.out.println("Servidor: ACK recibido de " + remitente);
                } else {
                    System.out.println("Servidor: Mensaje no-ACK de " + remitente + ": " + ack);
                }
            }

            System.out.println("Servidor: Todos los ACKs recibidos. Cerrando servidor.");
            socketServidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void manejarCliente(DatagramSocket socket, String mensaje, SocketAddress cliente) {
        try {
            byte[] datos = mensaje.getBytes();
            while (!ackRecibidos.get(cliente)) {
                DatagramPacket paquete = new DatagramPacket(datos, datos.length,
                        ((InetSocketAddress)cliente).getAddress(),
                        ((InetSocketAddress)cliente).getPort());
                socket.send(paquete);
                System.out.println("Servidor: Mensaje enviado a " + cliente);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
