package com.company.Commands;

import java.io.Serializable;

public class RemoveByIdCommand implements ICommand, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;

    public RemoveByIdCommand(int id){
        this.id = id;
    }
    @Override
    public void execute() {

    }
}
