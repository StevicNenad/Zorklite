package game.items.gems;

import game.Attributes;
import game.abilities.active.SoulSiphon;
import game.abilities.active.VitalitySwap;
import game.items.Gems;

public class VitalitySwapGem extends Gems {


    public VitalitySwapGem() {
        ability = new VitalitySwap();
        name = "Vitality Swap Gem";
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
