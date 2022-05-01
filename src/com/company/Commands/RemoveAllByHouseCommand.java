package com.company.Commands;

import com.company.data.House;

import java.io.Serializable;

public class RemoveAllByHouseCommand implements ICommand, Serializable {
    private static final long serialVersionUID = 2L;
    private House h;

    public RemoveAllByHouseCommand(House h){
        this.h = h;
    }

    @Override
    public void execute() {

    }
}
