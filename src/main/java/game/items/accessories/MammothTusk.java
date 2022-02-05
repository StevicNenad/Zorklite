package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class MammothTusk extends Accessories {

    public MammothTusk() {
        this.name = "Mammoth Tusk";
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
                0.25,
                0
        );
    }
}
