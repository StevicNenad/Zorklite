package game;

import java.util.HashMap;

public class Room {
    private Monster monster;
    private Item loot;
    private boolean secret;
    private HashMap<String, Room> exits;
    private Effect enviromental_effect;
}
