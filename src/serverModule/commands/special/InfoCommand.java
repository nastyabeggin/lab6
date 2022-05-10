package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.util.ResponseOutputer;

/**
 * Выводит информацию о коллекции
 */
public class InfoCommand extends AbstractCommand{
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции", collectionManager, "");
    }

    @Override
    public void execute(String params, Object objectArgument) throws CommandException {
        ResponseOutputer.append(collectionManager.getInfo() + "\n");
    }
}
