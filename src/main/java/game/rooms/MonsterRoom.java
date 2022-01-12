package game.rooms;

import game.Character;
import game.Item;
import game.Room;
import game.characters.Monster;

import java.util.ArrayList;
import java.util.HashMap;

public class MonsterRoom extends Room {

    public MonsterRoom() {
        this.roomType = RoomType.MONSTER;
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
    public HashMap<String, Room> getExits() {
        return super.getExits();
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
    public void setMonsters(ArrayList<Character> monsters) {
        super.setMonsters(monsters);
    }

    @Override
    public String generateDescription() {
        return super.generateDescription();
    }
}
