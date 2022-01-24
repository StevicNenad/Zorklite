package game.characters;

import game.Attributes;
import game.Character;
import game.items.Accessories;
import game.items.Armor;
import game.items.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character {
    private Weapon weapon;
    private Armor armor;
    private Accessories firstAcc, secondAcc;
    /*private Ability first, second, third;
    private ArrayList<ItemKey> keys;*/

    public Player() {

        attributes = new Attributes(
                5,
                0,
                0,
                4,
                2,
                2,
                6,
                5,
                1,
                0,
                0,
                0,
                0,
                0,
                0
        );

        maxHealth = attributes.calculateHealth();
        armorPoints = 0;
        ShieldPoints = 0;
        maxMana = attributes.calculateMana();

        weapon = null;
        armor = null;
        firstAcc = null;
        secondAcc = null;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        weapon.addStats(attributes);
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setAccessory(Accessories accessory) {
        if(firstAcc == null) {
            firstAcc = accessory;
        }
        else if(secondAcc == null) {
            secondAcc = accessory;
        }
        else {
            String user_choice = null;
            Scanner sc = new Scanner(System.in);

            System.out.println( "Accessory slots full. Which accessory do you wish to swap out?\n" +
                                "1 - " + firstAcc.getName() + "\n" +
                                "2 - " + secondAcc.getName() + "\n" +
                                "Any - Cancel");

            user_choice = sc.nextLine();

            switch(user_choice) {
                case "1":
                    firstAcc = accessory;
                    break;
                case "2":
                    secondAcc = accessory;
                    break;
            }
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public CharacterType getType() {
        return super.getType();
    }
}
