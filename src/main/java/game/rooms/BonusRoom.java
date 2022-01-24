package game.rooms;

import game.Item;
import game.Room;
import game.items.ItemFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BonusRoom extends Room {


    public BonusRoom(){
        explored = true;
        this.roomType = RoomType.BONUS;
        loot = new ArrayList<Item>();
        exits = new HashMap<String, Room>();
    }

    @Override
    public void generateDescription() {
        String description = "In this room there is ";
    }

    public void generateLoot() {
        ItemFactory itemFactory = new ItemFactory();
        Random rn = new Random();
        int itemType = rn.nextInt(2);

        switch(itemType) {
            case 0:
                this.loot.add(itemFactory.getRandomAccessory());
                break;
            case 1:
                this.loot.add(itemFactory.getRandomGem());
                break;
        }
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
}
