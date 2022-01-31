package game.characters;

import game.Ability;
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
    private ArrayList<Ability> active;
    private ArrayList<Ability> passive;
    /*private Ability first, second, third;
    private ArrayList<ItemKey> keys;*/

    public Player() {
        name = "Player";

        attributes = new Attributes(
                5,
                0,
                0,
                4,
                2,
                2,
                10,
                5,
                0,
                1,
                0,
                0,
                0,
                0,
                0
        );

        characterType = CharacterType.PLAYER;
        maxHealth = attributes.calculateHealth();
        currentHealth = maxHealth;
        armorPoints = 0;
        shieldPoints = 0;
        currentShield = shieldPoints;
        currentArmor = armorPoints;
        maxMana = attributes.calculateMana();
        currentMana = maxMana;

        calculateResistances();

        weapon = null;
        armor = null;
        firstAcc = null;
        secondAcc = null;
        passives = new ArrayList<Ability>();
        actives = new ArrayList<Ability>();
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        attributes.addStats(weapon.getAttributes());
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
        attributes.addStats(armor.getAttributes());
    }

    public void resetCharacter() {
        attributes.removeStats(weapon.getAttributes());
        weapon = null;

        attributes.removeStats(armor.getAttributes());
        armor = null;

        if(firstAcc != null) {
            attributes.removeStats(firstAcc.getAttributes());
            firstAcc = null;
        }
        if(secondAcc != null) {
            attributes.removeStats(secondAcc.getAttributes());
            secondAcc = null;
        }

        currentMana = maxMana;
        currentHealth = maxHealth;
        currentArmor = armorPoints;
        currentShield = shieldPoints;
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
