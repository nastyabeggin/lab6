package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import common.collection.LabWork;
import serverModule.commands.exceptions.ParamException;

import java.util.Objects;

/**
 * Добавляет лабу в коллекцию, если его минимальный балл меньше минимального
 */
public class AddIfMinCommand extends AbstractCommand {
    public AddIfMinCommand(CollectionManager collectionManager) {
        super("add_if_min", "добавить новый элемент в коллекцию, если его мин. балл меньше," +
                "   чем у наименьшего элемента этой коллекции", collectionManager, "{minimalPoint (float)}");
    }

    @Override
    public void execute(String commandParameters) throws ParamException{
        float minimalMinimalPoint = collectionManager.getMinimalMinimalPoint();
        if (Objects.equals(commandParameters, "")) throw new ParamException();
        try {
            float userMinimalPoint = Float.parseFloat(commandParameters);
            if (minimalMinimalPoint > userMinimalPoint) {
                LabWork newLabWork = collectionManager.generateNewIfMin(userMinimalPoint);
                collectionManager.add(newLabWork);
            } else {
                System.out.println("Ваше значение больше минимального");
            }
        } catch (Exception e) {
            throw new ParamException();
        }
    }

}
