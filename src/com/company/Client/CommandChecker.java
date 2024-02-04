package com.company.Client;

import com.company.Commands.*;
import com.company.Requests.FlatRequest;
import com.company.Requests.HouseRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CommandChecker {
    private String command;
    private int id;
    private String scriptFile;
    private ObjectOutputStream objectOutputStream;
    private String user;

    public CommandChecker(String command, ObjectOutputStream objectOutputStream, String user, String id) throws IOException {
        this.command = command;
        this.objectOutputStream = objectOutputStream;
        this.user = user;
        try {
            this.id = Integer.parseInt(id);
        }
        catch (NumberFormatException e){
            this.scriptFile = id;
        }

    }

    public ObjectOutputStream checker() throws IOException {

        {
            switch (command.trim()) {
                case "show":
                    objectOutputStream.writeObject(new ShowCommand());
                    break;
                case "reorder":
                    objectOutputStream.writeObject(new ReorderCommand());
                    break;
                case "add":
                    objectOutputStream.writeObject(new AddCommand(FlatRequest.request(user)));
                    break;
                case "clear":
                    objectOutputStream.writeObject(new ClearCommand(user));
                    break;
                case "average_of_number_of_rooms":
                    objectOutputStream.writeObject(new AverageOfNumberOfRooms());
                    break;
                case "max_by_furniture":
                    objectOutputStream.writeObject(new MaxByFurnitureCommand());
                    break;
                case "info":
                    objectOutputStream.writeObject(new InfoCommand());
                    break;
                case "help":
                    objectOutputStream.writeObject(new HelpCommand());
                    break;
                case "remove_by_id":
                    objectOutputStream.writeObject(new RemoveByIdCommand(id, user));
                    break;
                case "update_id":
                    objectOutputStream.writeObject(new UpdateIdCommand(FlatRequest.request(user), id, user));
                    break;
                case "add_if_min":
                    objectOutputStream.writeObject(new AddIfMinCommand(FlatRequest.request(user), id));
                    break;
                case "remove_all_by_house":
                    objectOutputStream.writeObject(new RemoveAllByHouseCommand(HouseRequest.request(), id, user));
                case "remove_lower":
                    objectOutputStream.writeObject(new RemoveLowerCommand(id, user));
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "execute_script":
                    try {
                        objectOutputStream.writeObject(new ExecuteScriptCommand(scriptFile, user));
                    }
                    catch (FileNotFoundException e){
                        System.out.println("File not found");
                    }
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }

            objectOutputStream.flush();
            return objectOutputStream;
        }
    }
}
