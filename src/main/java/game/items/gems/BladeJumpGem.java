package game.items.gems;

import game.Attributes;
import game.abilities.active.BladeJump;
import game.items.Gems;

public class BladeJumpGem extends Gems {

    public BladeJumpGem() {
        ability = new BladeJump();
        name = "Blade Jump Gem";
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
