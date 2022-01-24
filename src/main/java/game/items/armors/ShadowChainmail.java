package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class ShadowChainmail extends Armor {

    public ShadowChainmail() {
        this.name = "Shadow Chainmail";

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
                0.33,
                -0.2
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 75;
        this.speedModifier = 0.7;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
