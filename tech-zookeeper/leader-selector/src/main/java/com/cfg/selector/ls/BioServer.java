package com.cfg.selector.ls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lw
 * @since 2024/3/8
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept(); // 阻塞直到有客户端连接  
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // 为每个客户端创建一个新的线程来处理  
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Received from client: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
