package game;


import game.abilities.basic.Attack;

public class Character {
    protected enum CharacterType {
        BOSS,
        MONSTER,
        PLAYER
    }

    protected CharacterType characterType;

    protected String        name;

    protected int           maxHealth,
                            currentHealth,
                            maxMana,
                            currentMana,
                            armorPoints,
                            currentArmor,
                            shieldPoints,
                            currentShield,
                            experience,
                            lvlUpExperience;    //amount of experience required for level up.

    protected Attributes    attributes;
    //protected Ability       ability;

    public void calculateResistances() {
        attributes.setDamageReduction(currentArmor / 200);
        attributes.setMagicResistance(currentShield / 200);
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

    public void updateHealth(int healthPoints) {
        currentHealth = currentHealth + healthPoints;
    }

    public void updateArmor(int armor) {
        currentArmor = currentArmor + armor;

        if(currentArmor <= 0) {
            attributes.setDamageReduction(0);
        }
        else {
            attributes.setDamageReduction(currentArmor / 200);
        }
    }

    public void updateShield(int shield) {
        currentShield = currentShield + shield;

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
