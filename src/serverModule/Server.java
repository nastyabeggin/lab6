package serverModule;

import common.util.Request;
import common.util.Response;
import serverModule.util.User;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Scanner;

public class Server {
    private int port;
    private User user;
    private Selector selector;
    private Scanner scanner;
    private int numRead = -1;
    private int previous;
    private Response response;
    private boolean flag = true;
    private SocketAddress address;
    private ServerSocketChannel serverSocketChannel;
    private ByteBuffer readBuffer = ByteBuffer.allocate(2048); // выделение памяти под буфер

    public Server(int port, User user) throws IOException {
        this.port = port;
        this.user = user;
        selector = Selector.open();
        address = new InetSocketAddress(port);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(address);
        selector = SelectorProvider.provider().openSelector();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        checkInput();
    }

    private void closeServer() throws IOException {
        this.selector.close();
        this.serverSocketChannel.close();
    }

    private void checkInput() {
        scanner = new Scanner(System.in);
        Runnable userInput = () -> {
            try {
                while (flag) {
                    String[] userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    if (!userCommand[0].equals("save") && !userCommand[0].equals("exit")) {
                        System.out.println("Сервер не может сам принимать такую команду!");
                    }
                    if (userCommand[0].equals("exit")) {
                        System.out.println("Сервер заканчивает работу!");
                        serverSocketChannel.close();
                        closeServer();
                        this.flag = false;
                    }
                    if (userCommand[0].equals("save")) {
                        this.user.saveCollection();
                    }
                }
            } catch (Exception ignored) {
            }
        };
        Thread thread = new Thread(userInput);
        thread.start();
    }

    public void run() throws IOException {
        System.out.println("Сервер запущен!");
        while (flag) {
            try {
                //TODO Requests
                selector.select();
                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    if (!key.isValid())
                        continue;
                    if (key.isAcceptable()) {
                        accept(key);
                    }
                    if (key.isReadable())
                        read(key);
                    if (key.isWritable())
                        write(key);
                }
            } catch (ClosedSelectorException ignored) {
            }
        }
    }


    // Метод, реализующий получение нового подключения
    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) {
        try {
            Request request;
            SocketChannel socketChannel = (SocketChannel) key.channel();
            numRead = socketChannel.read(readBuffer);
            previous = readBuffer.position();
            ByteArrayInputStream bArrayIS = new ByteArrayInputStream(readBuffer.array());
            ObjectInputStream ois = new ObjectInputStream(bArrayIS);
            request = (Request) ois.readObject();
            String commandName = request.getCommandName();
            System.out.println("Получена команда: " + commandName);
            System.out.println("Выполнена команда: " + commandName);
            bArrayIS.close();
            ois.close();
            this.response = executeRequest(request);
            readBuffer.clear();
            SelectionKey selectionKey = socketChannel.keyFor(selector);
            selectionKey.interestOps(SelectionKey.OP_WRITE);
            selector.wakeup();

        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        socketChannel.write(ByteBuffer.wrap(buffer));
        SelectionKey selectionKey = socketChannel.keyFor(selector);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    private Response executeRequest(Request request) {
        return user.manage(request);
    }

}
