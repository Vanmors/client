package com.company;

import com.company.Commands.*;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) throws IOException {
//        try {
//            try {
//                // адрес - локальный хост, порт - 4004, такой же как у сервера
//                clientSocket = new Socket("127.0.0.1", 3245); // этой строкой мы запрашиваем
//                //  у сервера доступ на соединение
//                reader = new BufferedReader(new InputStreamReader(System.in));
//                // читать соообщения с сервера
////                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
////                // писать туда же
////                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//
//                System.out.println("Введите команду:");
//                // если соединение произошло и потоки успешно созданы - мы можем
//                //  работать дальше и предложить клиенту что то ввести
//                // если нет - вылетит исключение
//                String word = reader.readLine(); // ждём пока клиент что-нибудь
//                // не напишет в консоль
//                out.write(word + "\n"); // отправляем сообщение на сервер
//                out.flush();
////                in.read();
//                ByteBuffer buffer = ByteBuffer.allocate(1024);
////                buffer = ByteBuffer.allocateDirect(in.read());
//                int bytesread = in.read();
//                System.out.println(bytesread);
//                //String serverWord = in.readLine(); // ждём, что скажет сервер
//                //String request = (String) new ObjectInputStream(new ByteArrayInputStream(buffer.array())).readObject();
//                //System.out.println(request);
//                //System.out.println(p); // получив - выводим на экран
////            } catch (ClassNotFoundException e) {
////                e.printStackTrace();
//            } finally { // в любом случае необходимо закрыть сокет и потоки
//            System.out.println("Клиент был закрыт...");
//            clientSocket.close();
//            in.close();
//            out.close();
//        }
//        } catch (IOException e) {
//            System.err.println(e);
//        }

        while (true) {

            Scanner sc = new Scanner(System.in);
            if (!sc.hasNext()) {
                System.exit(0);
            }
            String command = sc.nextLine();
            sender(command);

        }
    }

    // write
//            String request = "help";
//            byte[] bs = request.getBytes(StandardCharsets.UTF_8);
//            ByteBuffer buffer = ByteBuffer.wrap(bs);
//            while (buffer.hasRemaining()) {
//                client.write(buffer);
//            }
    //write
    static public void sender(String command) {

            try {
                ClearCommand clearCommand = new ClearCommand();
                HelpCommand helpCommand = new HelpCommand();
                ShowCommand showCommand = new ShowCommand();
                ReorderCommand reorderCommand = new ReorderCommand();
                AverageOfNumberOfRooms average = new AverageOfNumberOfRooms();
                MaxByFurnitureCommand maxByFurnitureCommand = new MaxByFurnitureCommand();
                AddCommand addCommand = new AddCommand();
                InfoCommand infoCommand = new InfoCommand();
                try {
                    SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 3245));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    {
                        if (command.equals("show")) {
                            objectOutputStream.writeObject(showCommand);
                        } else if (command.equals("reorder")) {
                            objectOutputStream.writeObject(reorderCommand);
                        } else if (command.equals("add")) {
                            objectOutputStream.writeObject(addCommand.check());
                        } else if (command.equals("clear")) {
                            objectOutputStream.writeObject(clearCommand);
                        } else if (command.equals("average_of_number_of_rooms")) {
                            objectOutputStream.writeObject(average);
                        } else if (command.equals("max_by_furniture")) {
                            objectOutputStream.writeObject(maxByFurnitureCommand);
                        } else if (command.equals("info")) {
                            objectOutputStream.writeObject(infoCommand);
                        } else if (command.equals("help")) {
                            objectOutputStream.writeObject(helpCommand);
                        } else {
                            System.out.println("Неизветсная комманда");
                        }

                        objectOutputStream.flush();

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
//                    while (client.read(Reader) > 0) {
//                        System.out.printf("%s\n", new String(Reader.array(), StandardCharsets.UTF_8));
//                    }

                        client.close();
                    }
                }
                catch (ConnectException e){
                    System.out.println("Сервер не отвечает");
                }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        catch (ConnectException e) {
//            e.printStackTrace();
//        }
    }
}