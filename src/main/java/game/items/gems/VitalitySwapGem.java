package game.items.gems;

import game.abilities.active.SoulSiphon;
import game.abilities.active.VitalitySwap;
import game.items.Gems;

public class VitalitySwapGem extends Gems {


    public VitalitySwapGem() {
        ability = new VitalitySwap();
    }
}
