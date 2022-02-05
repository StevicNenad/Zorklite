package game.rooms;

import game.Item;
import game.Room;
import game.characters.Boss;

import java.util.ArrayList;
import java.util.HashMap;

public class TreasureRoom extends Room {

    public TreasureRoom() {
        explored = true;
        loot = new ArrayList<Item>();
        this.roomType = RoomType.TREASURY;
        exits = new HashMap<String, Room>();
    }

    @Override
    public void printMap() {
        super.printMap();
    }

    @Override
    public void generateDescription() {
        BossRoom.BossType bossType = ((BossRoom)exits.get("south")).getBossType();

        switch(bossType) {
            case NEPHILIM:
                description =   "You have defeated the Nephilim and are now in his chambers. There must be valueable things within.";
                break;
            case UNDERLORD:
                description =   "You have entered what must be the Underlords torture chambers. The stench is disgusting,\n" +
                                "but behind the huge pile of corpses you can make out a treasure chest...";
                break;
            case FALSEGOD:
                description =   "With the unborn defeated, you find yourself in a strange white room. It's completely empty,\n" +
                                "besides a golden chest right in the middle of it...";
                break;
        }
    }
}
