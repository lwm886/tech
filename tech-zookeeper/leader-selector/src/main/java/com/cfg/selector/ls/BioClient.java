package com.cfg.selector.ls;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author lw
 * @since 2024/3/8
 */
public class BioClient {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8080;
        Socket socket = new Socket(host, port);
        System.out.println("Connected to server at " + host + ":" + port);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            writer.write("Hello from client!");
            writer.newLine();
            writer.flush();
        }

        socket.close();
    }
}
