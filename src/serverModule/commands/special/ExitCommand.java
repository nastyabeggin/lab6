package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;

/**
 * Команда, завершающая программу (без сохранения)
 */
public class ExitCommand extends AbstractCommand {
    public ExitCommand(CollectionManager collectionManager) {
        super("exit", "завершить программу (без сохранения в файл)", collectionManager, "");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) {
        System.exit(0);
    }
}
