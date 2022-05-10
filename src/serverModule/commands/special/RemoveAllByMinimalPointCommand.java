package serverModule.commands.special;

import serverModule.commands.*;

import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.commands.exceptions.ParamException;
import serverModule.util.ResponseOutputer;

import java.util.Objects;

/**
 * Команда, удаляющая из коллекции все элементы, значение поля minimalPoint которого = данному
 */
public class RemoveAllByMinimalPointCommand extends AbstractCommand {
    public RemoveAllByMinimalPointCommand(CollectionManager collectionManager) {
        super("remove_all_by_minimal_point", "удалить из коллекции все элементы, значение поля minimalPoint которого эквивалентно заданному", collectionManager, "{minimalPoint (float)}");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) throws CommandException {
        if (Objects.equals(commandParameters, "")) throw new ParamException();
        try {
            float userMinimalPoint = Float.parseFloat(commandParameters);
            collectionManager.removeIf(labWork -> labWork.getMinimalPoint() == userMinimalPoint);
            ResponseOutputer.append("Команда выполнена \n");
        } catch (Exception e) {
            ResponseOutputer.append("Команда не выполнена. Проблема с данными \n");
            throw new ParamException();
        }
    }
}

