package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class PlateArmor extends Armor {

    public PlateArmor() {
        this.name = "Plate Armor";

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
                0.30,
                0
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 100;
        this.speedModifier = 0.5;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
