package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class DarkCloak extends Accessories {

    public DarkCloak() {
        this.name = "Dark Cloak";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                10,
                0,
                0,
                0,
                15,
                0,
                0,
                0,
                0,
                0,
                0
        );
    }
}
