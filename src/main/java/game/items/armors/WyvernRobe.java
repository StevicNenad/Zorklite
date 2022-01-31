package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class WyvernRobe extends Armor {

    public WyvernRobe() {
        this.name = "Wyvern Robe";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                10,
                1,
                0,
                0,
                0,
                0,
                0
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 10;
        this.speedModifier = 1.5;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
