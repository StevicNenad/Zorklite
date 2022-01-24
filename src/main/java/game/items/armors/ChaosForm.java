package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class ChaosForm extends Armor {

    public ChaosForm() {
        this.name = "Chaos Form";

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
                -0.25,
                -0.1
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 35;
        this.speedModifier = 1.5;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
