package game;

import game.characters.Player;

public class Item {
    protected enum ItemType {
        ACCESSORY,
        ARMOR,
        GEM,
        TOKENS,
        ESSENCE,
        POTION,
        WEAPON
    }

    protected int           value;

    protected String        name,
                            description;

    protected double        speedModifier,
                            experienceModifier;

    protected ItemType      itemType;
    protected Attributes    attributes;

    public void upgradeStats(int essences) {
        return;
    }

    public String getName() {
        return name;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public ItemType getitemType() {
        return itemType;
    }

    public double getSpeedModifier() {
        return speedModifier;
    }

    public int getValue() {
        return value;
    }
}
