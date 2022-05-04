package com.company;

import com.company.Client.CommandChecker;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {
            Scanner sc = new Scanner(System.in);
            if (!sc.hasNext()) {
                System.exit(0);
            }
            String command = sc.nextLine();
            String[] n = command.split(" ");
            try {
                sender(n[0], n[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                sender(n[0], "0");
            }
        }

    }

    static public void sender(String command, String id) {

        try {

            try {
                SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 4009));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                CommandChecker commandChecker = new CommandChecker(command, objectOutputStream, id);
                objectOutputStream = commandChecker.checker();
                {

                    byte[] bytes = byteArrayOutputStream.toByteArray();
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
                    client.close();
                }
            } catch (ConnectException e) {
                System.out.println("Сервер не отвечает");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}