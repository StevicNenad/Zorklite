package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class ScorcherRing extends Accessories {

    public ScorcherRing() {
        this.name = "Scorcher's Ring";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
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
                0,
                0,
                0,
                0.25,
                0
        );

        value = 500;
    }
}
