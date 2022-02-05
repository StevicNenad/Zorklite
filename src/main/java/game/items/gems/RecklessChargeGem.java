package game.items.gems;

import game.Attributes;
import game.abilities.active.HailMary;
import game.abilities.active.RecklessCharge;
import game.items.Gems;

public class RecklessChargeGem extends Gems {


    public RecklessChargeGem() {
        ability = new RecklessCharge();
        name = "Reckless Charge Gem";
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
