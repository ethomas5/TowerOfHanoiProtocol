import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 This program tests the tower server.
 */
public class TowerClient
{
    /***
     * A hard-coded example of a user playing the game, making everything from invalid moves, to
     * incorrect commands, to winning the game
     * @param args
     * @throws IOException once the user either quits out or wins the game
     */
    public static void main(String[] args) throws IOException
    {
        final int SBAP_PORT = 8890;
        try (Socket s = new Socket("localhost", SBAP_PORT))
        {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            // Showing an invalid DISPLAY request (before game is created)
            String command = "DISPLAY";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            System.out.println(in.nextLine());

            // Showing an invalid command
            command = "DADAWDAD";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            System.out.println(in.nextLine());

            // starting a new game with two disks
            command = "START 2";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());

            // showing an invalid move
            command = "MOVE 1 4";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());

            //showing a valid move
            command = "MOVE 0 1";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());

            //finishing the game
            command = "MOVE 0 2";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());

            command = "MOVE 1 2";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
        }
    }
}