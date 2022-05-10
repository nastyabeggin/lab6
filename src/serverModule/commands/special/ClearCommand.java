package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;

/**
 * Команда, очищающая коллекцию
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию", collectionManager, "");
    }

    @Override
    public void execute(String commandParameters) {
        collectionManager.clear();
    }
}
