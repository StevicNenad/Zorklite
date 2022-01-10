package game.items;

import game.abilities.passive.ExtraAttack;
import game.items.accessories.*;
import game.items.gems.ExtraAttackGem;

import java.util.ArrayList;
import java.util.Random;

public class ItemFactory {
    private ArrayList<Accessories>  accessoryList;
    private ArrayList<Gems>         gemList;

    public ItemFactory() {
        accessoryList = new ArrayList<Accessories>();
        gemList = new ArrayList<Gems>();

        //Create every type of accessory and add to ArrayList
        Bracelet bracelet = new Bracelet();
        accessoryList.add(bracelet);

        ChaosShard chaosShard = new ChaosShard();
        accessoryList.add(chaosShard);

        Headband headband = new Headband();
        accessoryList.add(headband);

        Necklace necklace = new Necklace();
        accessoryList.add(necklace);



        //Create every type of gem and add to ArrayList
        ExtraAttackGem extraAttackGem = new ExtraAttackGem();
        gemList.add(extraAttackGem);
    }

    public Accessories getRandomAccessory() {
        Random rn = new Random();
        int index = rn.nextInt(accessoryList.size());

        return accessoryList.get(index);
    }

    public Gems getRandomGem() {
        Random rn = new Random();
        int index = rn.nextInt(gemList.size());

        return gemList.get(index);
    }
}
