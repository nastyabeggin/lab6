package clientModule;

import clientModule.util.Console;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    private static final String HOST = "localhost";
    private static final int PORT = 8000;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        Client client = new Client(HOST, PORT, console);
        client.run();
        scanner.close();
    }

}
