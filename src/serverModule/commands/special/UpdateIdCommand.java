/*
package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.commands.exceptions.ParamException;

*/
/**
 * Команда, которая обновляет экземпляр коллекции
 *//*

public class UpdateIdCommand extends AbstractCommand {
    public UpdateIdCommand(CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", collectionManager, "{Id(long)}");
    }

    @Override
    public void execute(String params) throws CommandException {
        try {
            long userId = Long.parseLong(params);
            collectionManager.update(userId);
        } catch (Exception e) {
            throw new ParamException();
        }
    }
}
*/
