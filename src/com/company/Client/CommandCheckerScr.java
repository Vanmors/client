package com.company.Client;

import com.company.Commands.*;
import com.company.Requests.FlatRequest;
import com.company.Requests.HouseRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CommandCheckerScr {
    private String command;
    private int id;
    private String scriptFile;
    private ClearCommand clearCommand = new ClearCommand();
    private HelpCommand helpCommand = new HelpCommand();
    private ShowCommand showCommand = new ShowCommand();
    private ReorderCommand reorderCommand = new ReorderCommand();
    private AverageOfNumberOfRooms average = new AverageOfNumberOfRooms();
    private MaxByFurnitureCommand maxByFurnitureCommand = new MaxByFurnitureCommand();
    private AddCommand addCommand;
    private InfoCommand infoCommand = new InfoCommand();
    private ObjectOutputStream objectOutputStream;


    public CommandCheckerScr(String command, ObjectOutputStream objectOutputStream, String id) throws IOException {
        this.command = command;
        this.objectOutputStream = objectOutputStream;
        try {
            this.id = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            this.scriptFile = id;
        }
    }

    public ObjectOutputStream checker() throws IOException {

        {
            switch (command.trim()) {
                case "show":
                    objectOutputStream.writeObject(showCommand);
                    break;
                case "reorder":
                    objectOutputStream.writeObject(reorderCommand);
                    break;
                case "add":
                    objectOutputStream.writeObject(addCommand = new AddCommand(FlatRequest.request()));
                    break;
                case "clear":
                    objectOutputStream.writeObject(clearCommand);
                    break;
                case "average_of_number_of_rooms":
                    objectOutputStream.writeObject(average);
                    break;
                case "max_by_furniture":
                    objectOutputStream.writeObject(maxByFurnitureCommand);
                    break;
                case "info":
                    objectOutputStream.writeObject(infoCommand);
                    break;
                case "help":
                    objectOutputStream.writeObject(helpCommand);
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
                    objectOutputStream.writeObject(new RemoveAllByHouseCommand(HouseRequest.request()));
                case "remove_lower":
                    objectOutputStream.writeObject(new RemoveLowerCommand(id));
                    break;
                case "exit":
                    objectOutputStream.writeObject(new SaveCommand());
                    break;
                case "execute_script":
                    try {
                        ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand(scriptFile);
                        executeScriptCommand.executeScript();

                    } catch (FileNotFoundException e) {
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
