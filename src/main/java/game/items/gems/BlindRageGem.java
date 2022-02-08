package game.items.gems;

import game.Attributes;
import game.abilities.active.VitalitySwap;
import game.abilities.passive.BlindRage;
import game.items.Gems;

public class BlindRageGem extends Gems {


    public BlindRageGem() {
        ability = new BlindRage();
        name = "Blind Rage Gem";
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
