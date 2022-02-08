package game.items.gems;

import game.Attributes;
import game.abilities.passive.CounterAttack;
import game.abilities.passive.EmberInfusion;
import game.items.Gems;

public class EmberGem extends Gems {


    public EmberGem() {
        ability = new EmberInfusion();
        name = "Ember Gem";
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
