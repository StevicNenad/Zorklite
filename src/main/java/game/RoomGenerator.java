package game;

import game.characters.Boss;
import game.characters.Monster;
import game.characters.MonsterGenerator;
import game.characters.bosses.FalseGod;
import game.characters.bosses.Nephilim;
import game.characters.bosses.Underlord;
import game.rooms.BossRoom;
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

        generatedRoom.rollBonusRoom();
        generatedRoom.generateExits(roomNumber, generatedRoom);
        generatedRoom.generateDescription();
        return generatedRoom;
    }

    public Room generateBossRoom(int roomNumber) {
        generatedRoom = new BossRoom();
        Boss boss;

        if(roomNumber == 10) {
            boss = new Nephilim();
        }
        else if(roomNumber == 20) {
            boss = new Underlord();
        }
        else {
            boss = new FalseGod();
        }

        generatedRoom.setBoss(boss);
        generatedRoom.generateExits(roomNumber,generatedRoom);
        generatedRoom.generateDescription();
        return generatedRoom;
    }
}
