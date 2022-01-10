package game.items;

import game.items.armors.*;
import game.items.weapons.*;

import java.util.ArrayList;
import java.util.Random;

public class Armory {
    private ArrayList<Armor> armorList;
    private ArrayList<Weapon> weaponList;

    public Armory() {
        weaponList = new ArrayList<Weapon>();
        armorList = new ArrayList<Armor>();

        //Generate one of every Weapon and add to ArrayList
        Axe axe = new Axe();
        weaponList.add(axe);

        Bow bow = new Bow();
        weaponList.add(bow);

        Daggers daggers = new Daggers();
        weaponList.add(daggers);

        Greatsword greatsword = new Greatsword();
        weaponList.add(greatsword);

        Katana katana = new Katana();
        weaponList.add(katana);

        Repeater repeater = new Repeater();
        weaponList.add(repeater);

        Shuriken shuriken = new Shuriken();
        weaponList.add(shuriken);

        Spear spear = new Spear();
        weaponList.add(spear);

        Sword sword = new Sword();
        weaponList.add(sword);

        Wand wand = new Wand();
        weaponList.add(wand);


        //Generate one of every Armor and add to ArrayList
        LightArmor lightArmor = new LightArmor();
        armorList.add(lightArmor);

        HeavyArmor heavyArmor = new HeavyArmor();
        armorList.add(heavyArmor);

        MagicRobe magicRobe = new MagicRobe();
        armorList.add(magicRobe);

        ChaosForm chaosForm = new ChaosForm();
        armorList.add(chaosForm);

        DivineCuirrass divineCuirrass = new DivineCuirrass();
        armorList.add(divineCuirrass);

        PlateArmor plateArmor = new PlateArmor();
        armorList.add(plateArmor);

        ScaleArmor scaleArmor = new ScaleArmor();
        armorList.add(scaleArmor);

        ShadowChainmail shadowChainmail = new ShadowChainmail();
        armorList.add(shadowChainmail);

        TigerHide tigerHide = new TigerHide();
        armorList.add(tigerHide);

        WyvernRobe wyvernRobe = new WyvernRobe();
        armorList.add(wyvernRobe);
    }

    //Picks a random weapon from ArrayList and returns it
    public Weapon getRandomWeapon() {
        Random rn = new Random();
        int index = rn.nextInt(weaponList.size());

        return weaponList.get(index);
    }

    //Picks a random armor from ArrayList and returns it
    public Armor getRandomArmor() {
        Random rn = new Random();
        int index = rn.nextInt(armorList.size());

        return armorList.get(index);
    }
}
