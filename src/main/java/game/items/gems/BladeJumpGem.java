package game.items.gems;

import game.abilities.active.BladeJump;
import game.items.Gems;

public class BladeJumpGem extends Gems {


    public BladeJumpGem() {
        ability = new BladeJump();
    }
}
