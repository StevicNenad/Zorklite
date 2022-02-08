package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class SorcerersHat extends Accessories {

    public SorcerersHat() {
        this.name = "Sorcerers Hat";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                35,
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

        value = 500;
    }
}
