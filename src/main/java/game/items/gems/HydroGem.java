package game.items.gems;

import game.Attributes;
import game.abilities.passive.EscalatingViolence;
import game.abilities.passive.HydroTouch;
import game.items.Gems;

public class HydroGem extends Gems {


    public HydroGem() {
        ability = new HydroTouch();
        name = "Hydro Gem";
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
