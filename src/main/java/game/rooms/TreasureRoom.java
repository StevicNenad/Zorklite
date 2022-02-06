package game.rooms;

import game.Character;
import game.Item;
import game.Room;
import game.characters.Boss;

import java.util.ArrayList;
import java.util.HashMap;

public class TreasureRoom extends Room {
    private BossRoom.BossType bossType;

    public TreasureRoom() {
        monsters = new ArrayList<Character>();
        explored = true;
        loot = new ArrayList<Item>();
        roomType = RoomType.TREASURY;
        exits = new HashMap<String, Room>();
    }

    @Override
    public void printMap() {
        super.printMap();
    }

    @Override
    public void generateDescription() {
        switch(bossType) {
            case NEPHILIM:
                description =   "You have defeated the Nephilim and are now in his chambers. There must be valueable things within.";
                break;
            case UNDERLORD:
                description =   "You have entered what must be the Underlords torture chambers. The stench is disgusting,\n" +
                                "but behind the huge pile of corpses you can make out a treasure chest...";
                break;
        }
    }

    public BossRoom.BossType getBossType() {
        return bossType;
    }

    public void setBossType(BossRoom.BossType bossType) {
        this.bossType = bossType;
    }
}
