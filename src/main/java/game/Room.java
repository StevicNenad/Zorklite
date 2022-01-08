package game;

import java.util.HashMap;

public class Room {
    private Character monster;
    private Item loot;
    private boolean secret;
    private HashMap<String, Room> exits;
    //private Effect enviromental_effect;
    private BonusRoom bonus;
}
