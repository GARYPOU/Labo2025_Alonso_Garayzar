package Redes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

public class Hilo extends Thread{
    private DatagramPacket outPkt;
    private DatagramSocket sock;

    public Hilo(DatagramPacket outPkt, DatagramSocket sock) {
        super();
        this.outPkt = outPkt;
        this.sock = sock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            sock.send(outPkt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sent...");
    }
}

