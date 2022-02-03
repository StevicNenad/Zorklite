package game.items.gems;

import game.abilities.passive.EscalatingViolence;
import game.abilities.passive.HydroTouch;
import game.items.Gems;

public class HydroGem extends Gems {


    public HydroGem() {
        ability = new HydroTouch();
    }
}
