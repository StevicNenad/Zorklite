package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class BlazingGloves extends Accessories {

    public BlazingGloves() {
        this.name = "Blazing Fire Gloves";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                0,
                5,
                5,
                5,
                0,
                0,
                0,
                0,
                0,
                0.20,
                0,
                0,
                0
        );
    }
}
