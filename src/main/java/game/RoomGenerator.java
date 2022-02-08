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
                    double  spawnChance = rn.nextDouble();

                    numberEnemies = calculateNumberofMonsters(spawnChance, index);//Number of enemies chance increases the further you get into the game

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
                        Room bonus = generateBonusRoom(room, index);
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
                    room.getEnemies().add(boss);

                    Room treasury = generateTreasury(room, index);
                    room.setExit("north", treasury);

                    room.generateDescription();
                    break;
            }
            index++;
        }
    }

    private int calculateNumberofMonsters(double spawnChance, int index) {
        if(index < 10) {
            if(spawnChance <= 0.05) {
                return 0;
            }
            else if(spawnChance <= 0.10) {
                return 4;
            }
            else if(spawnChance <= 0.30) {
                return 3;
            }
            else if (spawnChance <= 0.60) {
                return 2;
            }
            else {
                return 1;
            }
        }
        else if(index < 20) {
            if(spawnChance <= 0.01) {
                return 0;
            }
            else if(spawnChance <= 0.05) {
                return 5;
            }
            else if(spawnChance <= 0.20) {
                return 4;
            }
            else if(spawnChance <= 0.45) {
                return 3;
            }
            else if(spawnChance <= 0.85) {
                return 2;
            }
            else {
                return 1;
            }
        }
        else {
            if(spawnChance <= 0.05) {
                return 1;
            }
            else if(spawnChance <= 0.15) {
                return 2;
            }
            else if(spawnChance <= 0.35) {
                return 3;
            }
            else if(spawnChance <= 0.75) {
                return 4;
            }
            else {
                return 5;
            }
        }
    }

    private Room generateBonusRoom(Room mainRoom, int index) {
        BonusRoom bonusRoom = new BonusRoom();
        ItemFactory iF = new ItemFactory();
        Random rn = new Random();
        double roll = rn.nextDouble();

        if(roll <= 0.40) {
            bonusRoom.getLoot().add(iF.getRandomGem());
        } else {
            bonusRoom.getLoot().add(iF.getRandomAccessory());
        }

        //calculate perception requirement
        int pReq = 5 + index;
        bonusRoom.setPerceptionRequirement(pReq);

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
        treasureRoom.setBossType(((BossRoom)mainRoom).getBossType());
        treasureRoom.getLoot().add(demonEssences);
        treasureRoom.getLoot().add(tokens);
        treasureRoom.generateDescription();

        return treasureRoom;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
