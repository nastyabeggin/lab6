package serverModule.commands.special;

import common.util.Response;
import serverModule.collection.CollectionManager;
import common.collection.LabWork;
import serverModule.commands.*;
import serverModule.util.ResponseOutputer;

/** Добавляет элемент в коллекцию
 *
 */
/* add {element} : добавить новый элемент в коллекцию */
public class AddCommand extends AbstractCommand {
    public AddCommand(CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию", collectionManager, "{LabWork}");
    }

    @Override
    public void execute(String commandParameters, Object objectArgument) {
        LabWork labWork = (LabWork) objectArgument;
        labWork.generateId(collectionManager);
        collectionManager.add(labWork);
        ResponseOutputer.append("Элемент успешно добавлен в коллекцию\n");
    }
}
