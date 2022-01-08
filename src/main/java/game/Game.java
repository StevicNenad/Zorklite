package game;

import java.util.ArrayList;

public class Game {
    private int roomNumber;
    private Room currentRoom;
    private ArrayList<Room> rooms;

    public Game() {
        roomNumber = 1;
        rooms = new ArrayList<Room>();
        currentRoom = new StartRoom();
    }


}
