package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class DemonicTalisman extends Accessories {

    public DemonicTalisman() {
        this.name = "Demonic Talisman";
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
                0,
                0,
                0,
                0,
                2,
                0,
                0
        );

        value = 500;
    }
}
