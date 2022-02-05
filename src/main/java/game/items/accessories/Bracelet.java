package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class Bracelet extends Accessories {

    public Bracelet() {
        this.name = "Power Bracelet";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
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
                0,
                0,
                0,
                0,
                0
        );
    }
}
