package game;

import game.characters.Boss;
import game.characters.Monster;
import game.characters.MonsterGenerator;
import game.characters.bosses.*;
import game.items.DemonEssences;
import game.items.ItemFactory;
import game.items.Tokens;
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
                    int numberEnemies = 0;
                    double spawnChance = rn.nextDouble();

                    if(spawnChance <= 0.05) {
                        numberEnemies = 0;
                    }
                    else if(spawnChance <= 0.12) {
                        numberEnemies = 5;
                    }
                    else if(spawnChance <= 0.25){
                        numberEnemies = 4;
                    }
                    else if (spawnChance <= 45) {
                        numberEnemies = 3;
                    }
                    else if (spawnChance <= 0.70) {
                        numberEnemies = 2;
                    }
                    else {
                        numberEnemies = 1;
                    }

                    for(int i = 0;  i < numberEnemies; i++) {
                        MonsterGenerator mgen = new MonsterGenerator();
                        int monsterLevel = rn.nextInt(3) + (index - 1);
                        Monster monster = mgen.getRandomMonster();
                        monster.getAttributes().levelScaleMonsters(monsterLevel);
                        monster.updateAllStatsAfterLevelup();
                        mgen.generateLoot(monster);

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

                    switch(index) {
                        case 11:
                            boss = new Nephilim();
                            ((BossRoom)room).setBossType(BossRoom.BossType.NEPHILIM);
                            break;
                        case 21:
                            boss = new Underlord();
                            ((BossRoom)room).setBossType(BossRoom.BossType.UNDERLORD);
                            break;
                        case 31:
                            boss = new FalseGod();
                            ((BossRoom)room).setBossType(BossRoom.BossType.FALSEGOD);
                            break;
                    }
                    room.setBoss(boss);

                    Room treasury = generateTreasury(room, index);
                    room.setExit("north", treasury);

                    room.generateDescription();
                    break;
            }
            index++;
        }
    }

    private Room generateBonusRoom(Room mainRoom) {
        BonusRoom bonusRoom = new BonusRoom();
        ItemFactory iF = new ItemFactory();
        Random rn = new Random();
        double roll = rn.nextDouble();

        if(roll <= 0.40) {
            bonusRoom.getLoot().add(iF.getRandomGem());
        } else {
            bonusRoom.getLoot().add(iF.getRandomAccessory());
        }

        bonusRoom.getLoot().add(iF.getPotion());
        bonusRoom.setExit("south", mainRoom);

        return bonusRoom;
    }

    private Room generateTreasury(Room mainRoom, int index) {
        TreasureRoom treasureRoom = new TreasureRoom();
        DemonEssences demonEssences = new DemonEssences();
        Tokens tokens = new Tokens();

        if(index == 11) {
            tokens.setValue(1000);
        } else if (index == 21) {
            tokens.setValue(3000);
        } else {
            tokens.setValue(5000);
        }
        treasureRoom.setExit("south", mainRoom);
        treasureRoom.getLoot().add(demonEssences);
        treasureRoom.getLoot().add(tokens);
        treasureRoom.generateDescription();

        return treasureRoom;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
