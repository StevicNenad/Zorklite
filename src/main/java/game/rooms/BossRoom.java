package game.rooms;

import game.Character;
import game.Item;
import game.Room;
import game.characters.Boss;
import game.characters.bosses.FalseGod;
import game.characters.bosses.Nephilim;
import game.characters.bosses.Underlord;

import java.util.ArrayList;
import java.util.HashMap;

public class BossRoom extends Room {

    public enum BossType {
        NEPHILIM,
        UNDERLORD,
        FALSEGOD
    }

    private BossType bossType;

    public BossRoom(){
        explored = true;
        roomType = RoomType.BOSS;
        exits = new HashMap<String, Room>();
        loot = new ArrayList<Item>();
        enemies = new ArrayList<Character>();
    }

    @Override
    public void generateDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        String description;

        if(enemies.isEmpty()) {
            stringBuilder.append("By some miracle, the abomination has been slain... ");

            stringBuilder.append("You can see " + exits.size() + " exit(s):\n");
            for(String direction : exits.keySet()) {
                String location = direction;
                String type = exits.get(direction).getRoomType().toString();
                stringBuilder.append("a " + type + " room in the " + location + "\n\nWhat do you wanna do?(type help for commands)\n");
            }
        }
        else {
            stringBuilder.append("You step into a big hall, you sense you are not alone. On closer inspection you can see ");
            stringBuilder.append(enemies.get(0).getName() + ". The sight of it disgusts you, but there is no way back now...\n");
        }

        description = stringBuilder.toString();

        this.description = description;
    }


    @Override
    public Room getExit(String direction) {
        return super.getExit(direction);
    }

    @Override
    public void setExit(String direction, Room room) {
        super.setExit(direction, room);
    }

    public void setBossType(BossType bossType) {
        this.bossType = bossType;
    }

    public BossType getBossType() {
        return bossType;
    }
}
