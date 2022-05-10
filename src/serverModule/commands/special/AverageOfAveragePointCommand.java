package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import common.collection.LabWork;

/**
 * Команда выводит среднее значение среднего балла по всем лабам
 */
public class AverageOfAveragePointCommand extends AbstractCommand {
    public AverageOfAveragePointCommand(CollectionManager collectionManager) {
        super("average_of_average_point", "вывести среднее значение поля averagePoint для всех элементов коллекции",
                collectionManager, "");
    }

    @Override
    public void execute(String commandParameters) {
        long sum = 0;
        for (LabWork labWork : collectionManager) {
            sum += (labWork.getAveragePoint());
        }
        System.out.println(sum / collectionManager.size());
    }
}
