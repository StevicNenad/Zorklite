package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class DivineCuirrass extends Armor {

    public DivineCuirrass() {
        this.name = "Divine Cuirrass";

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
                0.4,
                0.05
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 55;
        this.speedModifier = 0.9;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
