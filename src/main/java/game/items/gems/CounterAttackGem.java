package game.items.gems;

import game.abilities.passive.Blitz;
import game.abilities.passive.CounterAttack;
import game.items.Gems;

public class CounterAttackGem extends Gems {


    public CounterAttackGem() {
        ability = new CounterAttack();
    }
}
