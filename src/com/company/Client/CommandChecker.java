package com.company.Client;

import com.company.Commands.*;
import com.company.Requests.FlatRequest;
import com.company.Requests.HouseRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CommandChecker {
    private String command;
    private int id;
    private String scriptFile;
    private ObjectOutputStream objectOutputStream;


    public CommandChecker(String command, ObjectOutputStream objectOutputStream, String id) throws IOException {
        this.command = command;
        this.objectOutputStream = objectOutputStream;
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
                    objectOutputStream.writeObject(new AddCommand(FlatRequest.request()));
                    break;
                case "clear":
                    objectOutputStream.writeObject(new ClearCommand());
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
                    objectOutputStream.writeObject(new RemoveByIdCommand(id));
                    break;
                case "update":
                    objectOutputStream.writeObject(new UpdateIdCommand(FlatRequest.request(), id));
                    break;
                case "add_if_min":
                    objectOutputStream.writeObject(new AddIfMinCommand(FlatRequest.request(), id));
                    break;
                case "remove_all_by_house":
                    objectOutputStream.writeObject(new RemoveAllByHouseCommand(HouseRequest.request(), id));
                case "remove_lower":
                    objectOutputStream.writeObject(new RemoveLowerCommand(id));
                    break;
                case "exit":
                    objectOutputStream.writeObject(new SaveCommand());
                    break;
                case "execute_script":
                    try {
//                        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(scriptFile);
//                        executeScriptCommand.executeScript();
                        objectOutputStream.writeObject(new ExecuteScriptCommand(scriptFile));
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
