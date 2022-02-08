package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class Necklace extends Accessories {

    public Necklace() {
        this.name = "Gold Necklace";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                15,
                0,
                10,
                20,
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
