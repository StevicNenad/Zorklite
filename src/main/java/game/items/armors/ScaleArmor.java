package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class ScaleArmor extends Armor {

    public ScaleArmor() {
        this.name = "Scale Armor";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                8,
                1,
                0,
                0,
                0,
                0,
                0.05
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 45;
        this.speedModifier = 0.9;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
