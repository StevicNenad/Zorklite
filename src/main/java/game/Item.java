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

    public String getName() {
        return name;
    }

    public ItemType getitemType() {
        return itemType;
    }
}
