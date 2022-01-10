package game.items.armors;

import game.items.Armor;

public class WyvernRobe extends Armor {

    public WyvernRobe() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 10;
        this.damageReduction = -0.10;
        this.evasion = 0;
        this.speedModifier = 1.5;
        this.name = "Wyvern Robe";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
