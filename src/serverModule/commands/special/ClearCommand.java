package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.util.ResponseOutputer;

/**
 * Команда, очищающая коллекцию
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", collectionManager, "");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) {
        collectionManager.clear();
        ResponseOutputer.append("Коллекция очищена \n");
    }
}
