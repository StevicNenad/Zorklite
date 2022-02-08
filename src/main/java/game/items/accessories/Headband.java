package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class Headband extends Accessories {

    public Headband() {
        this.name = "White Headband";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                20,
                0,
                0,
                0,
                0,
                0,
                0,
                15,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        );

        value = 500;
    }
}
