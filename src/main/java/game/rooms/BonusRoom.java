package game.rooms;

import game.Item;
import game.Room;
import game.items.ItemFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BonusRoom extends Room {
    int perceptionRequirement;

    public BonusRoom(){
        explored = true;
        this.roomType = RoomType.BONUS;
        loot = new ArrayList<Item>();
        exits = new HashMap<String, Room>();

        generateDescription();
    }

    @Override
    public void generateDescription() {
        description = "There are items in this room:";

        for(Item item : loot) {
            description += "\n" + item.getName();
        }
        description += "\n";
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void printMap() {
        super.printMap();
    }

    @Override
    public void setExit(String direction, Room room) {
        super.setExit(direction, room);
    }

    public void setPerceptionRequirement(int perceptionRequirement) {
        this.perceptionRequirement = perceptionRequirement;
    }

    public int getPerceptionRequirement() {
        return perceptionRequirement;
    }
}
