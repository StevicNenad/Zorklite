package game.items.accessories;

import game.Attributes;
import game.items.Accessories;

public class SeraphicVisor extends Accessories {

    public SeraphicVisor() {
        this.name = "Seraphic Visor";
        description = "";
        this.itemType = ItemType.ACCESSORY;

        attributes = new Attributes(
                0,
                1,
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
                0,
                0,
                0
        );

        value = 500;
    }
}
