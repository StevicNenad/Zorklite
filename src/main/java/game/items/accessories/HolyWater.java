package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class HolyWater extends Accessories {

    public HolyWater() {
        this.name = "Bottle of Holy Water";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                1,
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
                0
        );

        value = 500;
    }
}
