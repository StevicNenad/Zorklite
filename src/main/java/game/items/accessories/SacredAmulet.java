package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class SacredAmulet extends Accessories {

    public SacredAmulet() {
        this.name = "Sacred Amulet";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                15,
                0,
                0,
                0,
                25,
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
