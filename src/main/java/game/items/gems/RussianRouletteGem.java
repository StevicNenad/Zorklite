package game.items.gems;

import game.abilities.active.RecklessCharge;
import game.abilities.active.RussianRoulette;
import game.items.Gems;

public class RussianRouletteGem extends Gems {


    public RussianRouletteGem() {
        ability = new RussianRoulette();
    }
}
