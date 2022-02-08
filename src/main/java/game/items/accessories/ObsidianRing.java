package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class ObsidianRing extends Accessories {

    public ObsidianRing() {
        this.name = "Obsidian Ring";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                5,
                0,
                0,
                5,
                5,
                5,
                5,
                5,
                5,
                0,
                0.1,
                0.2,
                0.5,
                0,
                0.1
        );

        value = 500;
    }
}
