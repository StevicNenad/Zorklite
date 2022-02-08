package game.items.gems;

import game.Attributes;
import game.abilities.passive.BlindRage;
import game.abilities.passive.Blitz;
import game.items.Gems;

public class BlitzGem extends Gems {


    public BlitzGem() {
        ability = new Blitz();
        name = "Blitz Gem";
        itemType = ItemType.GEM;
        attributes = new Attributes(
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
                0,
                0,
                0
        );

        value = 1500;

    }
}
