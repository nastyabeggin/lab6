package serverModule.commands.special;

import common.util.LabWorkBuilder;
import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import common.collection.LabWork;
import serverModule.commands.exceptions.ParamException;
import serverModule.util.ResponseOutputer;

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
    public void execute(String commandParameters, Object objectArgument) throws ParamException{
        float minimalMinimalPoint = collectionManager.getMinimalMinimalPoint();
        if (Objects.equals(commandParameters, "")) throw new ParamException();
        try {
            float userMinimalPoint = Float.parseFloat(commandParameters);
            if (minimalMinimalPoint > userMinimalPoint) {
                LabWork labWork = (LabWork) objectArgument;
                labWork.generateId(collectionManager);
                collectionManager.add(labWork);
                ResponseOutputer.append("Ваша лаба добавлена в коллекцию\n");
            } else {
                ResponseOutputer.append("Ваше значение больше минимального\n");
            }
        } catch (Exception e) {
            throw new ParamException();
        }
    }

}
