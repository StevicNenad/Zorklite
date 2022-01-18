package game;

import game.characters.Boss;
import game.characters.Monster;
import game.characters.MonsterGenerator;
import game.characters.bosses.*;
import game.rooms.*;

import java.util.ArrayList;
import java.util.Random;

public class RoomGenerator {

    private ArrayList<Room> rooms;
    private Room generatedRoom;

    public RoomGenerator() {
        rooms = new ArrayList<Room>();

        generateRooms();
    }

    private void populateArrayList() {
        for(int i = 0; i <= 30; i++) {
            if(i == 0) {
                generatedRoom = new StartRoom();
            }
            else if (i % 10 != 0) {
                generatedRoom = new MonsterRoom();
            }
            else {
                generatedRoom = new BossRoom();
            }
            rooms.add(generatedRoom);
        }
    }

    private void generateRooms() {
        populateArrayList();

        int index = 1;

        for(Room room : rooms) {
            switch(room.getRoomType()) {
                case START:
                    room.setExit("east", rooms.get(index));
                    break;

                case MONSTER:
                    Random rn = new Random();
                    MonsterGenerator mgen = new MonsterGenerator();
                    int numberEnemies = 0;
                    double spawnChance = rn.nextDouble();

                    if(spawnChance <= 0.10) {
                        numberEnemies = 0;
                    }
                    else if(spawnChance <= 0.25){
                        numberEnemies = 3;
                    }
                    else if (spawnChance <= 0.55) {
                        numberEnemies = 2;
                    }
                    else {
                        numberEnemies = 1;
                    }

                    for(int i = 0;  i < numberEnemies; i++) {
                        int monsterLevel = rn.nextInt(3) + (index - 1);
                        Monster monster = mgen.getRandomMonster();
                        monster.getAttributes().setLevel(monsterLevel);
                        room.addMonster(monster);
                    }

                    room.setExit("east", rooms.get(index));

                    if(room.hasBonus()){
                        Room bonus = generateBonusRoom(room);
                        room.setExit("north", bonus);
                    }

                    room.generateDescription();
                    break;

                case BOSS:
                    Boss boss = null;
                    if(index < 30) {
                        room.setExit("east", rooms.get(index));
                    }

                    Room treasury = generateTreasury(room);
                    room.setExit("north", treasury);

                    switch(index) {
                        case 11:
                            boss = new Nephilim();
                            break;
                        case 21:
                            boss = new Underlord();
                            break;
                        case 31:
                            boss = new FalseGod();
                            break;
                    }
                    room.setBoss(boss);
                    room.generateDescription();
                    break;
            }
            index++;
        }
    }

    private Room generateBonusRoom(Room mainRoom) {
        BonusRoom bonusRoom = new BonusRoom();
        bonusRoom.setExit("south", mainRoom);

        return bonusRoom;
    }

    private Room generateTreasury(Room mainRoom) {
        TreasureRoom treasureRoom = new TreasureRoom();
        treasureRoom.setExit("south", mainRoom);

        return treasureRoom;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
