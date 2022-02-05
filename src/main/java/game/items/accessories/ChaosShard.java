package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class ChaosShard extends Accessories {

    public ChaosShard() {
        this.name = "Chaos Shard";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                10,
                0,
                0,
                -1,
                -2,
                1,
                -3,
                4,
                -1,
                0,
                -0.01,
                0.05,
                0.50,
                0,
                0.25
        );
    }
}
