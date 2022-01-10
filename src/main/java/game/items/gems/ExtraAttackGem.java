package game.items.gems;

import game.abilities.passive.*;
import game.items.Gems;

public class ExtraAttackGem extends Gems {

    public ExtraAttackGem() {
        ability = new ExtraAttack();
    }
}
