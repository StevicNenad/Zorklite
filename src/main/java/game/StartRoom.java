package game;

import game.items.Armory;

import java.util.ArrayList;

public class StartRoom extends Room {
    private ArrayList<Item> setOne;
    private ArrayList<Item> setTwo;
    private ArrayList<Item> setThree;

    public StartRoom(){
        Armory armory = new Armory();
        setOne.add(armory.getRandomWeapon());
        setOne.add(armory.getRandomArmor());

        setTwo.add(armory.getRandomWeapon());
        setTwo.add(armory.getRandomArmor());

        setThree.add(armory.getRandomWeapon());
        setThree.add(armory.getRandomArmor());
    }
}
