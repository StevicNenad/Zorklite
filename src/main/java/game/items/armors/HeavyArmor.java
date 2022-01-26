package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class HeavyArmor extends Armor {

    public HeavyArmor() {
        this.name = "Heavy Armor";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                0,
                0,
                0,
                0,
                0
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 25;
        this.speedModifier = 1;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
