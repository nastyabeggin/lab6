package clientModule.util;

import clientModule.exceptions.ParamException;
import clientModule.exceptions.WrongInputException;
import common.collection.LabWork;
import common.util.CommandCode;
import common.util.LabWorkBuilder;
import common.util.Request;
import common.util.ResponseCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Console {
    private Scanner userScanner;

    public Console(Scanner scanner) {
        this.userScanner = scanner;
    }

    public Request interactiveMode(ResponseCode serverResponseCode) {
        String userInput;
        String[] userCommand = {"", ""};
        Scanner scanner = new Scanner(System.in);
        CommandCode commandCode = null;
        do {
            try {
                if (serverResponseCode == ResponseCode.SERVER_EXIT || serverResponseCode == ResponseCode.ERROR) {
                    throw new WrongInputException("Проблемы со вводом");
                }
                System.out.print("# ");
                userInput = scanner.nextLine().toLowerCase();
                userCommand = (userInput.trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandCode = checkCommand(userCommand[0], userCommand[1]);
            } catch (WrongInputException e) {
                System.out.println("Проблемы со вводом" + e);
            }
        } while (commandCode == CommandCode.ERROR || userCommand[0].isEmpty());
        try {
            switch (commandCode) {
                case ADD:
                case UPDATE:
                    LabWork labWork = LabWorkBuilder.buildLab();
                    return new Request(userCommand[0], userCommand[1], labWork);
                case SCRIPT:
                    File scriptFile = new File(userCommand[1]);
                    if (!scriptFile.exists()) throw new FileNotFoundException();
                    userScanner = new Scanner(scriptFile);
                    System.out.println("Выполняю скрипт '" + scriptFile.getName() + "'!");
                    break;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Файл со скриптом не найден!");

        }
        return new Request(userCommand[0], userCommand[1]);
        /*while (true) {
            try {
                System.out.print("# ");

                String[] input = scanner.nextLine().toLowerCase().split(" ");
                String inputParams = "";
                if (input.length > 1) {
                    inputParams = input[1];
                }
                String inputCommand = input[0];
                if (!user.hasCommand(inputCommand)) throw new NoSuchCommandException(inputCommand);
                else {
                    user.execCommand(inputCommand, inputParams, scanner);
                    collection.addToCommandHistory(inputCommand);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Неверная команда");
            } catch (NoSuchCommandException e) {
                System.out.println("Нет такой команды. Введите help для справки");
            } catch (NoSuchElementException e) {
                System.out.println("System.in закрыт");
                break;
            }
        }*/


    }

    private CommandCode checkCommand(String commandName, String commandArguments) {
        try {
            switch (commandName) {
                case "":
                    return CommandCode.ERROR;
                case "help":
                case "info":
                case "show":
                case "clear":
                case "history":
                case "average_of_average_point":
                    return CommandCode.SPECIAL;
                case "exit":
                    if (!commandArguments.isEmpty()) throw new ParamException();
                    return CommandCode.EXIT;
                case "add":
                    if (!commandArguments.isEmpty()) throw new ParamException();
                    return CommandCode.ADD;
                case "add_if_min":
                    if (commandArguments.isEmpty()) throw new ParamException();
                    return CommandCode.ADD;
                case "remove_all_by_minimal_point":
                case "count_less_than_average_point":
                case "remove_lower":
                    if (commandArguments.isEmpty()) throw new ParamException();
                    return CommandCode.PARAM;
                case "update":
                    if (commandArguments.isEmpty()) throw new ParamException();
                    return CommandCode.UPDATE;
                case "execute_script":
                    if (commandArguments.isEmpty()) throw new ParamException();
                    return CommandCode.SCRIPT;
                case "save":
                    System.out.println("Эта команда недоступна клиентам!");
                    return CommandCode.ERROR;
                default:
                    System.out.println("Команда '" + commandName + "' не найдена. Наберите 'help' для справки.");
                    return CommandCode.ERROR;
            }
        } catch (ParamException e) {
            System.out.println("Проверьте правильность ввода аргументов!");
            return CommandCode.ERROR;
        }
    }


    public static void print(Object toOut) {
        System.out.print(toOut);
    }


    public static void println(Object toOut) {
        System.out.println(toOut);
    }

}
