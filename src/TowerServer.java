import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 A server that executes the Simple Tower Access Protocol.
 */
public class TowerServer
{
    public static void main(String[] args) throws IOException
    {
        // start the server on SBAP_PORT port number
        PegCollection towerGame = new PegCollection(3);
        final int SBAP_PORT = 8890;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for clients to connect...");

        while (true)
        {
            // Watch for clients connection and accept connection to start the protocol
            Socket s = server.accept();
            System.out.println("Client connected.");
            TowerService service = new TowerService(s);
            Thread t = new Thread(service);
            t.start();
        }
    }
}