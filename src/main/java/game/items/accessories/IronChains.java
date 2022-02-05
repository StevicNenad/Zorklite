package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class IronChains extends Accessories {

    public IronChains() {
        this.name = "Iron Chains";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                0,
                0,
                30,
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
