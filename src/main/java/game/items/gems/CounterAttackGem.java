package game.items.gems;

import game.Attributes;
import game.abilities.passive.Blitz;
import game.abilities.passive.CounterAttack;
import game.items.Gems;

public class CounterAttackGem extends Gems {


    public CounterAttackGem() {
        ability = new CounterAttack();
        name = "Counter Attack Gem";
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
