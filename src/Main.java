import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Testing a game where number of disks (n) == 1
        // Status: Test Passed
//        PegCollection temp = new PegCollection(1);
//        System.out.println(temp);
//        System.out.println(temp.playerWin());
//        temp.moveDisk(temp.game[0], temp.game[2]);
//        System.out.println(temp);
//        System.out.println(temp.playerWin());

        // Testing a game where n == 2
        // Status: Test Passed
//        PegCollection temp = new PegCollection(2);
//        System.out.println(temp);
//        temp.moveDisk(temp.game[0], temp.game[1]);
//        System.out.println(temp);
//        temp.moveDisk(temp.game[0], temp.game[2]);
//        System.out.println(temp);
//        System.out.println(temp.playerWin());
//        temp.moveDisk(temp.game[1], temp.game[2]);
//        System.out.println(temp);
//        System.out.println(temp.playerWin());

        // Testing a game where n == 3
        // Status: Test Passed
        PegCollection temp = new PegCollection(3);
        System.out.println(temp);
        temp.moveDisk(temp.game[0], temp.game[2]);
        System.out.println(temp);
        temp.moveDisk(temp.game[0], temp.game[1]);
        System.out.println(temp);
        temp.moveDisk(temp.game[2], temp.game[1]);
        System.out.println(temp);
        temp.moveDisk(temp.game[0], temp.game[2]);
        System.out.println(temp);
        temp.moveDisk(temp.game[1], temp.game[0]);
        System.out.println(temp);
        temp.moveDisk(temp.game[1], temp.game[2]);
        System.out.println(temp);
        System.out.println(temp.playerWin());
        temp.moveDisk(temp.game[0], temp.game[2]);
        System.out.println(temp);
        System.out.println(temp.playerWin());


//        temp.moveDisk(0, 1);
//        System.out.println(temp);
//        System.out.println(temp);
//        System.out.println(temp.moveDisk(0, 1));
//        System.out.println(temp);
    }
}