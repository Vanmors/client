package com.company;

import com.company.Client.CommandChecker;
import com.company.Client.LogConnect;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws IOException {
        String user = null;
        Main m = new Main();
        String s = "";
        while (!s.equals("Entered")) {
            s = "";
            try {
                LogConnect logConnect = new LogConnect();
                SocketChannel client = logConnect.connect();
                user = logConnect.log(client);
                System.out.println(user);
                ByteBuffer buffer = ByteBuffer.allocate(30000);
                client.read(buffer);
                buffer.flip();
                try {
                    while (true) {
                        s = s + (char) buffer.get();
                    }
                } catch (BufferUnderflowException e) {
                    System.out.print("");
                }
                System.out.println(s);
                client.close();
            } catch (Exception e) {
                System.out.println("Сервер не отвечает");
            }
        }
        while (true) {

            Scanner sc = new Scanner(System.in);
            if (!sc.hasNext()) {
                System.exit(0);
            }
            String command = sc.nextLine();
            String[] n = command.split(" ");

            try {
                m.sender(n[0], n[1], user);
            } catch (ArrayIndexOutOfBoundsException e) {
                m.sender(n[0], "0", user);
            }
        }

    }

    public void sender(String command, String id, String user) {

        try {
            try {
                byte[] bytesUC = {-84, -19, 0, 5};

                LogConnect logConnect = new LogConnect();
                SocketChannel client = logConnect.connect();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                CommandChecker commandChecker = new CommandChecker(command, objectOutputStream, user, id);
                objectOutputStream = commandChecker.checker();

                byte[] bytes = byteArrayOutputStream.toByteArray();
                if (!Arrays.equals(bytesUC, bytes)) {
                    byteArrayOutputStream.flush();
                    ByteBuffer bufferWrite = ByteBuffer.allocate(1024);
                    bufferWrite.put(bytes);
                    //System.out.println(bufferWrite);
                    bufferWrite.flip();
                    client.write(bufferWrite);

                    ByteBuffer Reader = ByteBuffer.allocate(30000);
                    client.read(Reader);
                    String s = "";
                    Reader.flip();
                    try {
                        while (true) {
                            s = s + (char) Reader.get();
                        }
                    } catch (BufferUnderflowException e) {
                        System.out.print("");
                    }
                    System.out.println(s);
                    if (command.equals("exit")) {
                        System.exit(0);
                    }
                }
                client.close();
            } catch (ConnectException e) {
                System.out.println("Сервер не отвечает");
            }

        } catch (IOException e) {
            System.out.println("Сервер разорвал соединение");
        }
    }
}