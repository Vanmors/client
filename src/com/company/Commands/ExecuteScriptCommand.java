package com.company.Commands;

import com.company.Client.CommandCheckerScript;

import java.io.*;

public class ExecuteScriptCommand implements Serializable {
    private static final long serialVersionUID = 3L;
    private String scriptFile;
    private BufferedReader reader;

    public ExecuteScriptCommand(String scriptFile) throws IOException {
        this.scriptFile = scriptFile;
    }

    public void executeScript() throws IOException {

        try {
            reader = new BufferedReader(new FileReader(scriptFile));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        CommandCheckerScript commandCheckerScript = new CommandCheckerScript(reader);
        commandCheckerScript.scriptExecutor();
    }
}
