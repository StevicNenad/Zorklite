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
    private int deaths,
                demonicEssence;


    public Player() {
        name = "Player";
        shortName = "YOU";

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
        potions = 0;
        deathTokens = 0;
        demonicEssence = 0;
        attributePoints = 0;

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
        recalculateStats();
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
        attributes.addStats(armor.getAttributes());
        this.armorPoints = armor.getArmorPoints();
        this.shieldPoints = armor.getShieldPoints();
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
            attributes.addStats(firstAcc.getAttributes());
        }
        else if(secondAcc == null) {
            secondAcc = accessory;
            attributes.addStats(secondAcc.getAttributes());
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
                    attributes.removeStats(firstAcc.getAttributes());
                    firstAcc = accessory;
                    attributes.addStats(firstAcc.getAttributes());
                    break;
                case "2":
                    attributes.removeStats(secondAcc.getAttributes());
                    secondAcc = accessory;
                    attributes.addStats(secondAcc.getAttributes());
                    break;
            }
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getDemonicEssence() {
        return demonicEssence;
    }

    @Override
    public CharacterType getType() {
        return super.getType();
    }

    public void setDemonicEssence(int demonicEssence) {
        this.demonicEssence = demonicEssence;
    }
}
