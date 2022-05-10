/*
package serverModule.commands.special;

import serverModule.commands.*;
import serverModule.collection.CollectionManager;
import serverModule.commands.exceptions.CommandException;
import serverModule.commands.exceptions.ParamException;
import common.util.Request;
import serverModule.util.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

*/
/*execute_script file_name : считать и исполнить скрипт из
  указанного файла. В скрипте содержатся команды в таком же виде,
  в котором их вводит пользователь в интерактивном режиме.
 *//*

public class ExecuteScriptCommand extends AbstractCommand{
    public ExecuteScriptCommand(CollectionManager collectionManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла", collectionManager, "{file_name (String)}");
    }

    @Override
    public void execute(String params) throws ParamException{
        if (params.equals(""))
            throw new ParamException();
        else {
            File file = new File(params);
            if (!file.exists()) {System.out.println("Файла не существует"); throw new ParamException();}
            else if (file.exists() && !file.canRead()) {System.out.println("Файл существует, нет прав на чтение."); throw new ParamException();}
            //else if (file.exists() && !file.canExecute()) {System.out.println("Проверьте файл на выполнение"); throw new ParamException();}
            else {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (true) {
                    assert scanner != null;
                    if (!scanner.hasNextLine()) break;
                    String line = scanner.nextLine().trim();
                    List<String> collection = Arrays.asList(line.split(" "));
                    User user = new User(collectionManager);
                    if (collection.get(0).equals("execute_script")) {
                            System.out.println("В файле команда execute_script не выполняется");
                    }else {
                        try{
                            Request request = new Request(collection.get(0) + collection.get(1));
                            user.execCommand(request);
                        } catch (IndexOutOfBoundsException e){
                            Request request = new Request(collection.get(0));
                            user.execCommand(request);
                        }
                    }

                }
            }
        }

    }
}
*/
