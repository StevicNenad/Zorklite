package game.items.gems;

import game.abilities.passive.CounterAttack;
import game.abilities.passive.EmberInfusion;
import game.items.Gems;

public class EmberGem extends Gems {


    public EmberGem() {
        ability = new EmberInfusion();
    }
}
