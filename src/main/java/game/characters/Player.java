package game.characters;

import game.Attributes;
import game.Character;
import game.items.Accessories;
import game.items.Armor;
import game.items.Weapon;

import java.util.ArrayList;

public class Player extends Character {
    private Weapon weapon;
    private Armor armor;
    private Accessories firstAcc, secondAcc;
    /*private Ability first, second, third;
    private ArrayList<ItemKey> keys;*/

    public Player() {
        attributes = new Attributes(4, 2, 2, 10, 1, 0, 0, 0);
        health = attributes.calculateHealth();
        armorPoints = attributes.calculateArmor();
        mana = attributes.calculateMana();

        weapon = null;
        armor = null;
        firstAcc = null;
        secondAcc = null;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setFirstAcc(Accessories firstAcc) {
        this.firstAcc = firstAcc;
    }

    public void setSecondAcc(Accessories secondAcc) {
        this.secondAcc = secondAcc;
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
