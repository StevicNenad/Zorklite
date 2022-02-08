package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class LightningQuiver extends Accessories {

    public LightningQuiver() {
        this.name = "Lightning Quiver";
        description = "Adds an additional projectile.";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                1,
                0,
                10,
                0,
                0,
                0,
                0,
                0,
                0.1,
                0.1,
                0,
                0,
                0
        );

        value = 500;
    }
}
