package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class LightArmor extends Armor {

    public LightArmor() {
        this.name = "Light Armor";

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
                0.10,
                0.10
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 10;
        this.speedModifier = 1.2;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
