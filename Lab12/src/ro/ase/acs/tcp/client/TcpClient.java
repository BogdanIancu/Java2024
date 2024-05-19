package ro.ase.acs.tcp.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your name: ");
        String name = scanner.nextLine();

        try (Socket socket = new Socket("localhost", 7777)) {
            new Thread(() -> {
                while (true) {
                    try {
                        InputStream inputStream = socket.getInputStream();
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        String receivedMessage = dataInputStream.readUTF();
                        System.out.println(receivedMessage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

            while (true) {
                System.out.print("Message: ");
                String message = scanner.nextLine();
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF(name + ": " + message);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
