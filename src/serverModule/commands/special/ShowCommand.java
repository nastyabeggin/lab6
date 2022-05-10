package serverModule.commands.special;

import serverModule.commands.*;

import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.util.ResponseOutputer;

/**
 * Команда выводит информацию об элементах коллекции
 */
public class ShowCommand extends AbstractCommand{
    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", collectionManager, "");
    }

    @Override
    public void execute(String params, Object objectArgument) throws CommandException {
        collectionManager.objectsInfo();
    }
}
