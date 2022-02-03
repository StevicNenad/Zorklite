package game.items.gems;

import game.abilities.active.DivineLight;
import game.abilities.active.HailMary;
import game.items.Gems;

public class HailMaryGem extends Gems {


    public HailMaryGem() {
        ability = new HailMary();
    }
}
