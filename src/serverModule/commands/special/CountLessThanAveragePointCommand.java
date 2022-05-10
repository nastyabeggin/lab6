package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import common.collection.LabWork;
import serverModule.commands.exceptions.ParamException;
import serverModule.util.ResponseOutputer;

import java.util.Objects;

/**
 * Команда, которая выводит количество элементов, значение среднего балла которых меньше введенного
 */
public class CountLessThanAveragePointCommand extends AbstractCommand {
    public CountLessThanAveragePointCommand(CollectionManager collectionManager) {
        super("count_less_than_average_point", "вывести количество элементов, значение поля averagePoint которых меньше заданного",
                collectionManager, "{averagePoint (long)}");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) throws ParamException {
        if (Objects.equals(commandParameters, "")) throw new ParamException();
        try {
            int n = 0;
            long userAveragePoint = Long.parseLong(commandParameters);
            for (LabWork labWork : collectionManager) {
                if (labWork.getAveragePoint() < userAveragePoint) n++;
            }
            ResponseOutputer.append("У " + n + " лаб средний балл меньше");
        } catch (Exception e) {
            throw new ParamException();
        }
    }
}
