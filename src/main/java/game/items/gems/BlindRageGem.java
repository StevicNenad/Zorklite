package game.items.gems;

import game.abilities.active.VitalitySwap;
import game.abilities.passive.BlindRage;
import game.items.Gems;

public class BlindRageGem extends Gems {


    public BlindRageGem() {
        ability = new BlindRage();
    }
}
