package com.company.Commands;

import java.io.Serializable;

public class RemoveLowerCommand implements ICommand, Serializable {
    private static final long serialVersionUID = 3L;
    private int id;

    public RemoveLowerCommand(int id){
        this.id =  id;
    }

    @Override
    public void execute() {

    }
}
