package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int roomNumber;
    private Room currentRoom;
    private ArrayList<Room> rooms;

    public Game() {
        roomNumber = 1;
        rooms = new ArrayList<Room>();
        currentRoom = new StartRoom();
    }

    public void welcome() {
        Scanner sc = new Scanner(System.in);
        System.out.print(   "************************\n" +
                            "******* Zorklite *******\n" +
                            "************************\n\n" +
                            "Press any key to begin...");

        sc.nextLine();
    }

    public void launch() {
        
    }
}
