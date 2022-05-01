package com.company.Client;

import com.company.Commands.*;
import com.company.Requests.FlatRequest;
import com.company.Requests.HouseRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CommandChecker {
    private String command;
    private int id;
    private ClearCommand clearCommand = new ClearCommand();
    private HelpCommand helpCommand = new HelpCommand();
    private ShowCommand showCommand = new ShowCommand();
    private ReorderCommand reorderCommand = new ReorderCommand();
    private AverageOfNumberOfRooms average = new AverageOfNumberOfRooms();
    private MaxByFurnitureCommand maxByFurnitureCommand = new MaxByFurnitureCommand();
    private AddCommand addCommand;
    private InfoCommand infoCommand = new InfoCommand();
    private ObjectOutputStream objectOutputStream;
    private RemoveByIdCommand removeByIdCommand;
    private UpdateIdCommand updateIdCommand;

    public CommandChecker(String command, ObjectOutputStream objectOutputStream, int id) throws IOException {
        this.command = command;
        this.objectOutputStream = objectOutputStream;
        this.id = id;
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
                default:
                    System.out.println("Unknown command");
                    break;
            }

            objectOutputStream.flush();
            return objectOutputStream;
        }
    }
}
