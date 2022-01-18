package game.rooms;

import game.Character;
import game.Item;
import game.Room;
import game.RoomGenerator;
import game.characters.Boss;
import game.characters.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MonsterRoom extends Room {

    public MonsterRoom() {
        explored = false;
        roomType = RoomType.MONSTER;
        monsters = new ArrayList<Character>();
        exits = new HashMap<String, Room>();
        rollBonusRoom();
    }

    @Override
    public void addMonster(Monster monster) {
        super.addMonster(monster);
    }

    @Override
    public ArrayList<Character> getMonsters() {
        return super.getMonsters();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public RoomType getRoomType() {
        return super.getRoomType();
    }

    @Override
    public void setExits(HashMap<String, Room> exits) {
        super.setExits(exits);
    }

    @Override
    public void setBoss(Boss boss) {
        super.setBoss(boss);
    }

    @Override
    public void generateDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        String description;

        if(monsters.isEmpty()) {
            stringBuilder.append("You feel no more threats in this room ");
        }
        else {
            stringBuilder.append("You feel a threatening presence in this room. You are able to spot enemies:\n");

            for(Character monster: monsters) {
                stringBuilder.append("a " + monster.getName() + "\n");
            }
        }

        stringBuilder.append("You can see " + exits.size() + " exit(s):\n");
        for(String direction : exits.keySet()) {
            String location = direction;
            String type = exits.get(direction).getRoomType().toString();
            stringBuilder.append("a " + type + " room in the " + location + "\n");
        }

        stringBuilder.append("\nWhat do you wanna do?(type help for commands)\n");

        description = stringBuilder.toString();

        this.description = description;
    }

    @Override
    public void rollBonusRoom() {
        Random rn = new Random();
        double chance = rn.nextDouble();

        if(chance < 0.05) {
            this.bonus = true;
        }
        else{
            this.bonus = false;
        }
    }


    @Override
    public void printDescription() {
        super.printDescription();
    }

    @Override
    public boolean hasBonus() {
        return super.hasBonus();
    }

    @Override
    public boolean isExplored() {
        return super.isExplored();
    }

    @Override
    public Room getExit(String direction) {
        return super.getExit(direction);
    }

    @Override
    public void setExit(String direction, Room room) {
        super.setExit(direction, room);
    }
}
