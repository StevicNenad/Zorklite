package game;


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
                            ShieldPoints,
                            experience,
                            lvlUpExperience;    //amount of experience required for level up.

    protected Attributes    attributes;
    //protected Ability       ability;


    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrenthealth(){
        return currentHealth;
    }

    public void updateHealth(int healthPoints) {
        currentHealth = currentHealth + healthPoints;
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
