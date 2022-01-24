package game;


public class Character {
    protected enum CharacterType {
        BOSS,
        MONSTER,
        PLAYER
    }

    protected CharacterType characterType;

    protected String        name;

    protected int           health,
                            mana,
                            armorPoints;

    protected Attributes    attributes;
    protected Ability       ability;


    public int getHealth() {
        return health;
    }

    public void updateHealth(int healthPoints) {
        health = health + healthPoints;
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
