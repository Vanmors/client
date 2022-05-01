package com.company.Commands;

import com.company.data.Flat;

import java.io.Serializable;

public class AddIfMinCommand implements ICommand, Serializable {
    private static final long serialVersionUID = 2L;
    private Flat f;
    private int id;

    public AddIfMinCommand(Flat f, int id){
        this.f = f;
        this.id = id;
    }

    @Override
    public void execute() {

    }
}
