package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class HandWraps extends Accessories {

    public HandWraps() {
        this.name = "Red Hand Wraps";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                0,
                10,
                0,
                10,
                10,
                0,
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
