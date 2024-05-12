package ro.ase.acs.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UdpServer extends Thread {
    private DatagramSocket serverSocket;

    public static void main(String[] args) {
        int port = 7777;
        Scanner scanner=new Scanner(System.in);

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Server started on port: " + port);
            byte[] buffer = new byte[256];
            DatagramPacket packetToBeReceived =
                    new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(packetToBeReceived);
            String receivedMessage = new String(packetToBeReceived.getData());
            System.out.println(receivedMessage);
            UdpServer server = new UdpServer();
            server.serverSocket = serverSocket;
            server.start();

            while(true){
                System.out.println("Message: ");
                String message = scanner.nextLine();
                byte[] content = message.getBytes();
                DatagramPacket packetToBeSent=
                        new DatagramPacket(content,content.length,
                                packetToBeReceived.getAddress(),
                                packetToBeReceived.getPort());
                serverSocket.send(packetToBeSent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                byte[] buffer = new byte[256];
                DatagramPacket packetToBeReceived =
                        new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packetToBeReceived);

                String receivedMessage = new String(
                        packetToBeReceived.getData());
                System.out.println(receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
