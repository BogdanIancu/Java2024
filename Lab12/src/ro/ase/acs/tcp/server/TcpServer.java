package ro.ase.acs.tcp.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class TcpServer {
    private static int port = 7777;
    private static List<Socket> clients = new Vector<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                clients.add(socket);

                new Thread(() -> {
                    while (true) {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            DataInputStream dataInputStream = new DataInputStream(inputStream);
                            String message = dataInputStream.readUTF();

                            for (Socket client : clients) {
                                if (socket != client) {
                                    OutputStream outputStream = client.getOutputStream();
                                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                                    dataOutputStream.writeUTF(message);
                                }
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
