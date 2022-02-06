package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class WolfFang extends Accessories {

    public WolfFang() {
        this.name = "Wolf Fang";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                20,
                0,
                0,
                5,
                0,
                0,
                10,
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
