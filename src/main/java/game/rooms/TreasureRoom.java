package game.rooms;

import game.Room;

import java.util.HashMap;

public class TreasureRoom extends Room {

    public TreasureRoom() {
        explored = true;
        this.roomType = RoomType.TREASURY;
        exits = new HashMap<String, Room>();
    }
}
