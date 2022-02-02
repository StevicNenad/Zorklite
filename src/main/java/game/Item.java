package game;

import game.characters.Player;

public class Item {
    protected enum ItemType {
        ACCESSORY,
        ARMOR,
        GEM,
        WEAPON
    }

    protected String        name;

    protected double        speedModifier,
                            experienceModifier;

    protected ItemType      itemType;
    protected Attributes    attributes;

    public String getName() {
        return name;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public ItemType getitemType() {
        return itemType;
    }

    public void upgradeStats(int essences) {

    }

}
