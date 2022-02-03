package game.items.gems;

import game.abilities.active.HailMary;
import game.abilities.active.RecklessCharge;
import game.items.Gems;

public class RecklessChargeGem extends Gems {


    public RecklessChargeGem() {
        ability = new RecklessCharge();
    }
}
