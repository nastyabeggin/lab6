package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.util.ResponseOutputer;

/**
 * Команда, которая выводит последние 5 команд
 */
public class HistoryCommand extends AbstractCommand{
    public HistoryCommand(CollectionManager collectionManager) {
        super("history", "вывести последние 5 команд (без их аргументов)", collectionManager, "");
    }

    @Override
    public void execute(String params) throws CommandException {
        ResponseOutputer.append(collectionManager.getCommandHistory());
    }
}
