package game;

import game.characters.Boss;
import game.characters.Monster;
import game.characters.Player;
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
    protected Boss boss;
    protected ArrayList<Character> monsters;
    protected ArrayList<Item> loot;
    protected HashMap<String, Room> exits;
    protected RoomType roomType;
    protected boolean bonus, treasury;
    protected boolean explored;

    public void generateDescription(){
    }

    public void rollBonusRoom(){
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public void printDescription() {
        System.out.print(description);
    }

    public void printMap() {

        if(!explored) {
            System.out.println("You haven't \"explored\" this room yet...");
            return;
        }

        System.out.println("Monsters:");
        if(monsters.isEmpty()) {
            System.out.println("none");
        }
        else {
            for(Character monster : monsters) {
                System.out.println(monster.getName() + ", lvl " + monster.getAttributes().getLevel());
            }
        }
        System.out.println("\nItems:");
        if(loot.isEmpty()) {
            System.out.println("none");
        }
        else {
            for (Item item : loot) {
                System.out.println(item.getName());
            }
        }
        System.out.println("\nExits:");
        for(String exit : exits.keySet()) {
            RoomType type = exits.get(exit).getRoomType();
            System.out.println(exit + ", " + type.toString().toLowerCase() + " room door");
        }
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

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLoot(ArrayList<Item> loot) {
        this.loot = loot;
    }

    public void setExits(HashMap<String, Room> exits) {
        this.exits = exits;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public boolean isExplored() {
        return explored;
    }

    public boolean hasBonus() {
        return bonus;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    //Used in StartRoom
    public void chooseLoadout(Player player){
    }
}
