package game.items.gems;

import game.abilities.passive.BlindRage;
import game.abilities.passive.Blitz;
import game.items.Gems;

public class BlitzGem extends Gems {


    public BlitzGem() {
        ability = new Blitz();
    }
}
