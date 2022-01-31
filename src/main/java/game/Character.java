package game;

import java.util.ArrayList;

public class Character {
    protected enum CharacterType {
        BOSS,
        MONSTER,
        PLAYER
    }

    protected CharacterType         characterType;

    protected String                name;

    protected int                   maxHealth,
                                    currentHealth,
                                    maxMana,
                                    currentMana,
                                    armorPoints,
                                    currentArmor,
                                    shieldPoints,
                                    currentShield,
                                    experience,
                                    lvlUpExperience;    //amount of experience required for level up.

    protected Attributes            attributes;
    protected ArrayList<Ability>    actives;
    protected ArrayList<Ability>    passives;

    public void calculateResistances() {
        attributes.setDamageReduction(currentArmor / 200);
        attributes.setMagicResistance(currentShield / 200);
    }

    public void updateHealthManaArmorShieldPoints() {
        maxHealth = attributes.calculateHealth();
        currentHealth = maxHealth;

        maxMana = attributes.calculateMana();
        currentMana = maxMana;

        armorPoints = attributes.calculateArmor();
        currentArmor = armorPoints;

        shieldPoints = attributes.calculateShield();
        currentShield = shieldPoints;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrenthealth(){
        return currentHealth;
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    public int getShieldPoints() {
        return shieldPoints;
    }

    public int getCurrentArmor() {
        return currentArmor;
    }

    public int getCurrentShield() {
        return currentShield;
    }

    public ArrayList<Ability> getPassives() {
        return passives;
    }

    public ArrayList<Ability> getActives() {
        return actives;
    }

    public void updateHealth(int healthPoints) {
        if(currentHealth + healthPoints <= maxHealth) {
            currentHealth = currentHealth + healthPoints;
        } else {
            currentHealth = maxHealth;
        }
    }

    public void updateArmor(int armor) {
        if(currentArmor + armor <= armorPoints) {
            currentArmor = currentArmor + armor;
        } else {
            currentArmor = armorPoints;
        }

        if(currentArmor <= 0) {
            attributes.setDamageReduction(0);
        }
        else {
            attributes.setDamageReduction(currentArmor / 200);
        }
    }

    public void updateShield(int shield) {
        if(currentShield + shield <= shieldPoints) {
            currentShield = currentShield + shield;
        } else {
            currentShield = shieldPoints;
        }

        if(currentShield <= 0) {
            attributes.setMagicResistance(0);
        }
        else {
            attributes.setMagicResistance(currentShield / 200);
        }
    }

    public CharacterType getType() {
        return characterType;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

}
