package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.util.ResponseOutputer;
import serverModule.util.User;

/**
 * Выводит доступные команды и справку
 */
public class HelpCommand extends AbstractCommand{
    public HelpCommand(CollectionManager collectionManager) {
        super("help", "вывести справку по доступным командам", collectionManager, "");
    }

    @Override
    public void execute(String params) throws CommandException {
        User tempUser = new User(collectionManager);
        ResponseOutputer.append("\tСписок доступных команд\t\n");
        for(Command command : tempUser.getAllCommands()){
            ResponseOutputer.append(command.getName() + "\t-\t" + command.getDescription() + "\n");
        }
    }
}
