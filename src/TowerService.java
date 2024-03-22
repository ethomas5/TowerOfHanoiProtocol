import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 Executes Simple Bank Access Protocol commands
 from a socket.
 */
public class TowerService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private PegCollection game = null;

    /**
     * Constructs a service object that processes commands
     * from a socket for a bank.
     *
     * @param aSocket the socket
     */
    public TowerService(Socket aSocket) {
        s = aSocket;
    }

    public void run() {
        try {
            try {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            } finally {
                s.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Executes all commands until the QUIT command or the
     * end of input.
     */
    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }
            String command = in.next();
            if (command.equals("QUIT")) {
                return;
            } else {
                executeCommand(command);
            }
        }
    }

    /**
     * Takes in a command and executes the specific directions as written in the protocol
     *
     * @param command the command to execute
     */
    public void executeCommand(String command) throws IOException {
        if (command.equals("START")) {
            int numDisks;
            try {
                numDisks = in.nextInt();
            } catch (InputMismatchException e) {
                out.println("This is not a valid number of disks");
                out.flush();
                return;
            }
            if (numDisks < 1) {
                out.println("This is not a valid number of disks");
                out.flush();
                return;
            } else {
                game = new PegCollection(numDisks);
                out.println("Game has been started with " + numDisks + " disks!");
            }
        } else if (command.equals("MOVE")) {
            int moveFrom;
            int moveTo;
            try {
                moveFrom = in.nextInt();
                moveTo = in.nextInt();
            } catch (InputMismatchException e) {
                out.println("This is not a correct input for a valid move");
                out.flush();
                return;
            }
            boolean wasMoved;
            try {
                wasMoved = game.moveDisk(game.game[moveFrom], game.game[moveTo]);
            } catch (Exception e) {
                out.println("This is not a valid move!");
                out.flush();
                return;
            }
            if (wasMoved) {
                out.println("Top disk was moved from peg " + moveFrom + " to peg " + moveTo + "!");
                if (game.playerWin()) {
                    out.println(game);
                    out.println("You have won the game!");
                    out.flush();
                    s.close();
                }
            } else {
                out.println("This is not a valid move!");
                out.flush();
                return;
            }
        } else if (!command.equals("DISPLAY")) {
            out.println("Invalid command");
            out.flush();
            return;
        }
        if (game != null) {
            out.println(game.toString());
        } else {
            out.println("Could not print any current game!");
            out.flush();
        }
        out.flush();
    }
}
