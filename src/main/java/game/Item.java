package game;

public class Item {
    protected enum ItemType {
        ACCESSORY,
        ARMOR,
        GEM,
        WEAPON
    }
    protected ItemType itemType;
    protected String name;
    protected int level;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public ItemType getitemType() {
        return itemType;
    }
}
