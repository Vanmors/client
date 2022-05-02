package com.company.Client;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class CommandCheckerScript {
    private BufferedReader reader;
    private ObjectOutputStream objectOutputStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private CommandCheckerScr commandCheckerScr;
    public CommandCheckerScript(BufferedReader reader) {
        this.reader = reader;
    }
    public void scriptExecutor() throws IOException {
    String line;
            while ((line = reader.readLine()) != null) {
                SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 4009));

                String[] n = line.split(" ");
                try {
                    commandCheckerScr = new CommandCheckerScr(n[0], objectOutputStream, n[1]);
                }
                catch (ArrayIndexOutOfBoundsException e){
                    commandCheckerScr = new CommandCheckerScr(n[0], objectOutputStream, "0");
                }
                objectOutputStream = commandCheckerScr.checker();
                byte[] bytes = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                ByteBuffer bufferWrite = ByteBuffer.allocate(1024);
                bufferWrite.put(bytes);
                System.out.println(bufferWrite);
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
                if (n[0].equals("exit")) {
                    System.exit(0);
                }
                client.close();
            }

    }
}
