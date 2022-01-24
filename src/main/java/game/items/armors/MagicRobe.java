package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class MagicRobe extends Armor {

    public MagicRobe() {
        this.name = "Magic Robe";

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
                0
        );
        
        this.itemType = ItemType.ARMOR;
        this.armorPoints = 5;
        this.shieldPoints = 50;
        this.speedModifier = 1.3;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
