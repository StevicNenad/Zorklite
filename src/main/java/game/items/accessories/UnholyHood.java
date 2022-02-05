package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class UnholyHood extends Accessories {

    public UnholyHood() {
        this.name = "Unholy Hood";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                200,
                0,
                0,
                -50,
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
