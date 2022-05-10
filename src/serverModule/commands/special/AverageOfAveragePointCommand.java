package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import common.collection.LabWork;
import serverModule.util.ResponseOutputer;

/**
 * Команда выводит среднее значение среднего балла по всем лабам
 */
public class AverageOfAveragePointCommand extends AbstractCommand {
    public AverageOfAveragePointCommand(CollectionManager collectionManager) {
        super("average_of_average_point", "вывести среднее значение поля averagePoint для всех элементов коллекции",
                collectionManager, "");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) {
        long sum = 0;
        for (LabWork labWork : collectionManager) {
            sum += (labWork.getAveragePoint());
        }
        ResponseOutputer.append(sum / collectionManager.size() + "\n");
    }
}
