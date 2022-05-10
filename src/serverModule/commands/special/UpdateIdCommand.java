package serverModule.commands.special;

import common.collection.LabWork;
import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.commands.exceptions.ParamException;
import serverModule.util.ResponseOutputer;

/**
 * Команда, которая обновляет экземпляр коллекции
 */

public class UpdateIdCommand extends AbstractCommand {
    public UpdateIdCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", collectionManager, "{Id(long)}");
    }

    @Override
    public void execute(String params, Object objectArgument) throws CommandException {
        try {
            long userId = Long.parseLong(params);
            collectionManager.update(userId, (LabWork) objectArgument);
            ResponseOutputer.append("Команда выполнена \n");
        } catch (Exception e) {
            ResponseOutputer.append("Команда не выполнена. Возникла ошибка \n");
            throw new ParamException();
        }
    }
}

