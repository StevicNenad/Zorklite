package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class TigerHide extends Armor {

    public TigerHide() {
        this.name = "Tiger Hide";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                20,
                1,
                0,
                0,
                0,
                0,
                0.2
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
