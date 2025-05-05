package org.example.lesson_33_network_communication;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader consoleReader =
                     new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = 
                     new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer =
                     new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            System.out.println("[CLIENT] Connected to the server");

            Thread listener = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            listener.start();

            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                writer.write(userInput);
                writer.newLine();
                writer.flush();

                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            System.out.println("[CLIENT] Disconnected from the server");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
