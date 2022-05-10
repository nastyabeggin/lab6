package serverModule.commands.special;

import serverModule.collection.CollectionManager;
import common.collection.LabWork;
import serverModule.commands.*;

/** Добавляет элемент в коллекцию
 *
 */
/* add {element} : добавить новый элемент в коллекцию */
public class AddCommand extends AbstractCommand {
    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию", collectionManager, "{LabWork}");
    }

    @Override
    public void execute(String commandParameters) {
        LabWork newLabWork = collectionManager.generateNew();
        collectionManager.add(newLabWork);
    }
}
