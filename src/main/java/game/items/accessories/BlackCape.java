package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class BlackCape extends Accessories {

    public BlackCape() {
        name = "Black Cape";
        description = "";
        itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                0,
                5,
                10,
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
