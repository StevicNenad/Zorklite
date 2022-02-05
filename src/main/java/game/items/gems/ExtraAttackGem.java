package game.items.gems;

import game.Attributes;
import game.abilities.passive.ExtraAttack;
import game.items.Gems;

public class ExtraAttackGem extends Gems {


    public ExtraAttackGem() {
        ability = new ExtraAttack();
        name = "Extra Attack Gem";
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
