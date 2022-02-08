package game.items.gems;

import game.Attributes;
import game.abilities.active.RecklessCharge;
import game.abilities.active.RussianRoulette;
import game.items.Gems;

public class RussianRouletteGem extends Gems {


    public RussianRouletteGem() {
        ability = new RussianRoulette();
        name = "Russian Roulette Gem";
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
