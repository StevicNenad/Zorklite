package game;


public class Character {

    protected String        type,
                            name;

    protected int           health,
                            mana,
                            armorPoints;

    protected Attributes    attributes;
    protected Ability       ability;


    public int getHealth() {
        return health;
    }

    public String getType() {
        return type;
    }
}
