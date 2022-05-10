package serverModule.commands;

import serverModule.commands.exceptions.CommandException;

/* интерфейс выполнения команды, который реализуют все классы команд */
public interface Command {
    /**
     * Метод для взаимодействия с коллекцией
     * @param params - аргументы, которые передаются с командой
     */
    void execute(String params, Object objectArgument) throws CommandException;
    String getName();
    String getDescription();
    void addToHistory();
    String getCommandHistory();
    String getParams();
}
