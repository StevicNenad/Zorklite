package game.rooms;

import game.Item;
import game.Room;
import game.items.Armory;

import java.util.ArrayList;

public class StartRoom extends Room {
    private ArrayList<Item> setOne;
    private ArrayList<Item> setTwo;
    private ArrayList<Item> setThree;

    public StartRoom(){
        setOne = new ArrayList<Item>();
        setTwo = new ArrayList<Item>();
        setThree = new ArrayList<Item>();

        this.roomType = RoomType.START;
        Armory armory = new Armory();
        setOne.add(armory.getRandomWeapon());
        setOne.add(armory.getRandomArmor());

        setTwo.add(armory.getRandomWeapon());
        setTwo.add(armory.getRandomArmor());

        setThree.add(armory.getRandomWeapon());
        setThree.add(armory.getRandomArmor());

        generateDescription();
    }

    @Override
    public void generateDescription() {
        String description =    "You are in a dark dungeon. You have no idea how you came to this place,\n" +
                                "but you feel the only way to get out is to push forward. In front of you\n" +
                                "there are three chests. You have to choose one. Inside of them you find:\n\n" +

                                "1st Chest\t\t\t\t2nd Chest\t\t\t\t3rd Chest\n" +
                                "Weapon:\t\t\t\t\tWeapon:\t\t\t\t\tWeapon:\n" +
                                setOne.get(0).getName() + "\t\t\t\t" + setTwo.get(0).getName() + "\t\t\t\t" +
                                setThree.get(0).getName() + "\n" +

                                "Armor:\t\t\t\t\tArmor:\t\t\t\t\tArmor:\n" +
                                setOne.get(1).getName() + "\t\t\t\t" + setTwo.get(1).getName() + "\t\t\t\t" +
                                setThree.get(1).getName() + "\n\nChoose (1-3): ";

        this.description = description;
    }

    public ArrayList<Item> getSetOne() {
        return setOne;
    }

    public ArrayList<Item> getSetTwo() {
        return setTwo;
    }

    public ArrayList<Item> getSetThree() {
        return setThree;
    }
}
