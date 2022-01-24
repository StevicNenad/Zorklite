package game.rooms;

import game.Item;
import game.Room;

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
}
