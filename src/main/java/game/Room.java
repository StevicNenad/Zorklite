package game;

import game.characters.Monster;
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

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Character> getMonsters() {
        return monsters;
    }

    public ArrayList<Item> getLoot() {
        return loot;
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMonsters(ArrayList<Character> monsters) {
        this.monsters = monsters;
    }

    public void setLoot(ArrayList<Item> loot) {
        this.loot = loot;
    }

    public void setExits(HashMap<String, Room> exits) {
        this.exits = exits;
    }
}
