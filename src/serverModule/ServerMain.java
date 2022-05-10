package serverModule;

import serverModule.collection.CollectionManager;
import serverModule.util.User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerMain {
    public static final int PORT = 8000;

    public static void main(String[] args) throws IOException {
        CollectionManager collectionManager = new CollectionManager();
        User user = new User(collectionManager);
        Server server = new Server(PORT, user);
        server.run();
    }
}
