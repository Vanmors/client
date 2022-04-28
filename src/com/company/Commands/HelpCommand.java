package com.company.Commands;

import java.io.Serializable;

public class HelpCommand implements Serializable, ICommand {
    private static final long serialVersionUID = 6529685098267757690L;
    @Override
    public void execute() {
        System.out.println("START");
    }
}
