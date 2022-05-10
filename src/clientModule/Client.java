package clientModule;

import clientModule.exceptions.WrongInputException;
import common.util.Response;
import common.util.Request;
import clientModule.util.Console;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private final static String PATH = System.getenv().get("sixth_project");
    private Console console;
    private final String HOST;
    private final int PORT;
    private SocketChannel socketChannel;
    private SocketAddress address;
    boolean flag = true;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(16384);

    public Client(String host, int port, Console console) {
        this.HOST = host;
        this.PORT = port;
        this.console = console;
    }

    public void run() {
        try {
            socketChannel = SocketChannel.open();
            address = new InetSocketAddress("localhost", this.PORT);
            socketChannel.connect(address);
            System.out.println("Соединение с сервером установлено");
            //makeByteBufferToRequest(new Request("loadCollection", this.PORT));
            //socketChannel.write(byteBuffer);
            //byteBuffer.clear();
            //socketChannel.read(byteBuffer);
            //Response responseLoading = deserialize();
            //System.out.println(responseLoading.getResponseBody());
            Request requestToServer = null;
            Response serverResponse = null;
            do {
                try {
                    requestToServer = serverResponse != null ? console.interactiveMode(serverResponse.getResponseCode()) :
                            console.interactiveMode(null);
                    if (requestToServer.isEmpty()) continue;
                    if (requestToServer.getCommandName().equals("exit")) {
                        flag = false;
                        break;
                    }
                    if (flag) {
                        makeByteBufferToRequest(requestToServer);
                        socketChannel.write(byteBuffer);
                        byteBuffer.clear();
                        socketChannel.read(byteBuffer);
                        serverResponse = deserialize();
                        System.out.print(serverResponse.getResponseBody());
                    }
                } catch (NullPointerException ignored) {
                }
            } while (!requestToServer.getCommandName().equals("exit") && flag);
        } catch (IOException | ClassNotFoundException | WrongInputException exception) {
            System.out.println("Произошла ошибка при работе с сервером!" + exception);
        }
        try {
            if (!flag) {
                socketChannel.close();
            }
        } catch (IOException ignored) {
        }
    }


    private void makeByteBufferToRequest(Request request) throws IOException {
        byteBuffer.put(serialize(request));
        byteBuffer.flip();
    }

    private byte[] serialize(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }

    private Response deserialize() throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        byteBuffer.clear();
        return response;
    }


}
