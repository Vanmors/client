package com.company.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class LogConnect {
//    private SocketChannel client;
//    private String user;

//    public LogConnect(SocketChannel client, String user){
//        this.client = client;
//        this.user = user;
//    }
    public SocketChannel connect() throws IOException {
        SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6123));
        return client;
    }

    public String log(SocketChannel client) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("login:");
        if (!scanner.hasNext()) {
            System.exit(0);
        }
        String login = scanner.nextLine();
        while ((login.trim()).equals("")) {
            System.out.println("login:");
            if (!scanner.hasNextLine()) {
                System.exit(0);
            }
            login = scanner.nextLine();
        }

        System.out.println("password:");
        if (!scanner.hasNext()) {
            System.exit(0);
        }
        String password = scanner.nextLine();
        while ((password.trim()).equals("")) {
            System.out.println("password:");
            if (!scanner.hasNextLine()) {
                System.exit(0);
            }
            password = scanner.nextLine();
        }

        String SendLog ="password " + login + " " + password;
        //System.out.println(SendLog);

        byte[] bs = SendLog.getBytes(StandardCharsets.UTF_8);
        ByteBuffer bufferWriter = ByteBuffer.wrap(bs);

        client.write(bufferWriter);

        return login;
    }
}
