package game;

import game.characters.Monster;
import game.characters.MonsterGenerator;
import game.rooms.MonsterRoom;

import java.util.Random;

public class RoomGenerator {

    private Room generatedRoom;

    public RoomGenerator() {
        generatedRoom = null;
    }

    public Room generateMonsterRoom(int roomNumber) {
        generatedRoom = new MonsterRoom();
        Random rn = new Random();
        MonsterGenerator mongen = new MonsterGenerator();

        //Loop that adds 1-3 Monsters to the room
        for(int i = 0;  i < rn.nextInt(3) + 1; i++) {
            int monsterLevel = rn.nextInt(3) + roomNumber;
            Monster monster = mongen.getRandomMonster();
            monster.getAttributes().setLevel(monsterLevel);
            generatedRoom.addMonster(monster);
        }

        return generatedRoom;
    }
}
