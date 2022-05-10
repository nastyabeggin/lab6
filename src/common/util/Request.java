package common.util;

import common.collection.LabWork;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private String commandParameters;
    private Serializable objectArgument;

    public Request(String input, Serializable objectArgument) {
        String[] inputArray = input.toLowerCase().split(" ");
        String inputParams = "";
        if (inputArray.length > 1) {
            inputParams = inputArray[1];
        }
        String inputCommand = inputArray[0];
        this.commandName = inputCommand;
        this.commandParameters = inputParams;
        this.objectArgument = objectArgument;
    }

    public Request(String commandName, String commandParameters) {
        this.commandName = commandName;
        this.commandParameters = commandParameters;
        this.objectArgument = null;
    }

    public Request() {
        this.commandName = "";
        this.commandParameters = "";
        this.objectArgument = null;
    }

    public Request(String commandName, String commandParameters, Serializable object) {
        this.commandName = commandName;
        this.commandParameters = commandParameters;
        this.objectArgument = object;

    }

    public Serializable getObjectArgument() {
        return objectArgument;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandParameters(String commandParameters) {
        this.commandParameters = commandParameters;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandParameters() {
        return commandParameters;
    }

    public boolean isEmpty() {
        return commandName.isEmpty() && commandParameters.isEmpty() && objectArgument == null;
    }
}
