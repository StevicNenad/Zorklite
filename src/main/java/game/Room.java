package game;

import game.rooms.BonusRoom;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    public enum RoomType {
        START,
        BONUS,
        MONSTER,
        BOSS,
        TREASURY
    }

    protected String description;
    protected ArrayList<Character> monsters;
    protected ArrayList<Item> loot;
    protected HashMap<String, Room> exits;
    //private Effect enviromental_effect;
    protected RoomType roomType;

    public String generateDescription(){
        return null;
    }

    public String getDescription() {
        return description;
    }


}
