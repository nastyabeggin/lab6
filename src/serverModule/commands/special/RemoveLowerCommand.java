package serverModule.commands.special;

import serverModule.commands.*;

import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.commands.exceptions.ParamException;
import serverModule.util.ResponseOutputer;

import java.util.Objects;

/**
 * Команда удаляет элементы Average Point которых меньше заданного
 */
public class RemoveLowerCommand extends AbstractCommand {
    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный", collectionManager, "{averagePont(long)}");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) throws CommandException {
        {
            if (Objects.equals(commandParameters, "")) throw new ParamException();
            try {
                long userAveragePoint = Long.parseLong(commandParameters);
                collectionManager.removeIf(labWork -> labWork.getAveragePoint() == userAveragePoint);
                ResponseOutputer.append("Команда выполнена \n");
            } catch (Exception e) {
                ResponseOutputer.append("Команда не выполнена. Проблема с аргументами \n");
                throw new ParamException();
            }
        }
    }
}

