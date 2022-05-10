package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.commands.exceptions.ParamException;
import serverModule.util.ResponseOutputer;

import java.util.Objects;

/**
 * Команда, удаляющая элемент коллекции по id
 */
public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id", collectionManager, "{id (long)}");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) throws CommandException {
        if (Objects.equals(commandParameters, "")) throw new ParamException();
        try {
            long userId = Long.parseLong(commandParameters);
            collectionManager.removeIf(labWork -> labWork.getId() == userId);
            ResponseOutputer.append("Команда выполнена \n");
        } catch (Exception e) {
            ResponseOutputer.append("Команда не выполнена. Проблема с аргументами \n");
            throw new ParamException();
        }
    }
}

