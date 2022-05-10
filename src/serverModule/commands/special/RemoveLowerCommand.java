package serverModule.commands.special;

import serverModule.commands.*;

import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.commands.exceptions.ParamException;

import java.util.Objects;

/**
 * Команда удаляет элементы Average Point которых меньше заданного
 */
public class RemoveLowerCommand extends AbstractCommand {
    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный", collectionManager, "{averagePont(long)}");
    }

    @Override
    public void execute(String commandParameters) throws CommandException {
        {
            if (Objects.equals(commandParameters, "")) throw new ParamException();
            try {
                long userAveragePoint = Long.parseLong(commandParameters);
                collectionManager.removeIf(labWork -> labWork.getAveragePoint() == userAveragePoint);
            } catch (Exception e) {
                throw new ParamException();
            }
        }
    }
}

