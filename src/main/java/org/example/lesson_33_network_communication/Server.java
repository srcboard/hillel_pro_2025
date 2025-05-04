package org.example.lesson_33_network_communication;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    private static int clientCount = 0;
    private static final Map<Socket, ClientInfo> activeConnections = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("[SERVER] Server started on port " + serverSocket.getLocalPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientCount++;
                String clientName = "client-" + clientCount;
                ClientInfo clientInfo = new ClientInfo(clientName, LocalDateTime.now(), clientSocket);
                activeConnections.put(clientSocket, clientInfo);

                System.out.println("[SERVER] " + clientName + " connected successfully");

                new Thread(new ClientHandler(clientSocket, clientInfo)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private final ClientInfo clientInfo;

        public ClientHandler(Socket socket, ClientInfo clientInfo) {
            this.socket = socket;
            this.clientInfo = clientInfo;
        }

        @Override
        public void run() {
            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer =
                         new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        writer.write("[SERVER] You have been disconnected\n");
                        writer.flush();
                        break;
                    } else {
                        writer.write("[SERVER] Unknown command: " + line + "\n");
                        writer.flush();
                    }
                }

            } catch (IOException e) {
                System.out.println("[SERVER] Connection error with " + clientInfo.name);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                activeConnections.remove(socket);
                System.out.println("[SERVER] " + clientInfo.name + " disconnected");
            }
        }
    }

    static class ClientInfo {
        String name;
        LocalDateTime connectedAt;
        Socket socket;

        ClientInfo(String name, LocalDateTime connectedAt, Socket socket) {
            this.name = name;
            this.connectedAt = connectedAt;
            this.socket = socket;
        }
    }
}
