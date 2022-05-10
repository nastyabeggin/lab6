package serverModule.util;

import common.util.Request;
import common.util.Response;
import common.util.ResponseCode;
import serverModule.collection.CollectionManager;
import serverModule.commands.Command;
import serverModule.commands.CommandHistory;
import serverModule.commands.special.*;
import serverModule.commands.exceptions.ParamException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Класс пользователя, который вызывает выполнение команд
 */
public class User {

    HashMap<String, Command> commandMap = new HashMap<String, Command>();
    CollectionManager collectionManager = new CollectionManager();

    public void addCommand(Command command) {
        commandMap.put(command.getName(), command);
    }

    public boolean hasCommand(String commandName) {
        return commandMap.containsKey(commandName);
    }

    public Collection<Command> getAllCommands() {
        return commandMap.values();
    }

    public Response manage(Request request) {
        try {
            ResponseCode responseCode = execCommand(request.getCommandName(), request.getCommandParameters(), request.getObjectArgument());
            String response = ResponseOutputer.getString();
            ResponseOutputer.clear();
            return new Response(responseCode, response);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public ResponseCode execCommand(String commandName, String commandParameters, Object objectArgument) {

        while (true) {
            try {
                commandMap.get(commandName).execute(commandParameters, objectArgument);
                this.collectionManager.addToCommandHistory(commandName);
                return ResponseCode.OK;
            } catch (ParamException e) {
                return ResponseCode.ERROR;
                //commandParameters = scanner.nextLine().split(" ")[0];
            }
        }
    }

    public void saveCollection(){
        this.collectionManager.saveToFile();
    }

    public User(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        addCommand(new AddCommand(collectionManager));
        addCommand(new ShowCommand(collectionManager));
        addCommand(new HelpCommand(collectionManager));
        addCommand(new AddIfMinCommand(collectionManager));
        addCommand(new AverageOfAveragePointCommand(collectionManager));
        addCommand(new ClearCommand(collectionManager));
        addCommand(new CountLessThanAveragePointCommand(collectionManager));
        addCommand(new HistoryCommand(collectionManager));
        addCommand(new InfoCommand(collectionManager));
        addCommand(new RemoveLowerCommand(collectionManager));
        addCommand(new RemoveByIdCommand(collectionManager));
        addCommand(new RemoveAllByMinimalPointCommand(collectionManager));
        addCommand(new UpdateIdCommand(collectionManager));
        addCommand(new ExecuteScriptCommand(collectionManager));
    }

}
