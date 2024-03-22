import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class PegCollection {
    public Peg[] game = new Peg[3];
    private int numDisks;

    public PegCollection(int numDisksParam) {
        game[0] = new Peg(numDisksParam, numDisksParam);
        game[1] = new Peg(0, numDisksParam);
        game[2] = new Peg(0, numDisksParam);
        numDisks = numDisksParam;
    }

    /***
     * Attempt to make a move with the topmost disk on a peg to another one. Will not throw errors, only returning
     * the result of the move
     * @param curr the peg you want to move a disk from
     * @param next the peg you want to move a disk to
     * @return true if the move was valid, false if not
     */
    public boolean moveDisk(Peg curr, Peg next) {
       if (curr.getTopDiskSize() == -1) {
           return false;
       }
       try {
           next.add(curr.getTopDiskSize());
       } catch (Exception e) {
           return false;
       }
       curr.removeFromTop();
       return true;
   }

    /***
     * Simple check to see if the game state is currently classified as a win for the player
     * @return whether the right-most peg has the max number of disks (won game)
     */
    public boolean playerWin() {
        return game[2].getSize() == numDisks && game[2].getTopDiskSize() == 1;
    }


    /***
     * Will write of the game into a string, including "buffer" disks to ensure that each peg is the same height regardless
     * of the disks it currently holds
     * @return console representation of the entire game
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < numDisks; i++) {
            for (int j = 0; j < 3; j++) {
                int bufferDiskNum = numDisks - game[j].getSize();
                if (bufferDiskNum > i) {
                    str = str + " ".repeat(numDisks) + "|" + " ".repeat(numDisks) + " ";
                } else {
                    str = str + game[j].printLevel(i - bufferDiskNum) + " ";
                }
            }
            str = str + "\n";
        }
        return str;
    }
}
