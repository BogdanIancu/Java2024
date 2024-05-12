package ro.ase.acs.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (DatagramSocket clientSocket = new DatagramSocket()) {

            new Thread(() -> {
                while (true) {
                    byte[] buffer = new byte[256];
                    DatagramPacket packetToBeReceived =
                            new DatagramPacket(buffer, buffer.length);
                    try {
                        clientSocket.receive(packetToBeReceived);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String receivedMessage = new String(
                            packetToBeReceived.getData());
                    System.out.println(receivedMessage);
                }
            }).start();

            while (true) {
                System.out.println("Message: ");
                String message = scanner.nextLine();
                byte[] content = message.getBytes();
                InetAddress address =
                        InetAddress.getByName("localhost");
                DatagramPacket packetToBeSent =
                        new DatagramPacket(content,
                                content.length, address, 7777);
                clientSocket.send(packetToBeSent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
