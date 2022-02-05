package game.items.gems;

import game.Attributes;
import game.abilities.active.DivineLight;
import game.abilities.active.HailMary;
import game.items.Gems;

public class HailMaryGem extends Gems {


    public HailMaryGem() {
        ability = new HailMary();
        name = "Hail Mary Gem";
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
    }
}
