package game.items.gems;

import game.Attributes;
import game.abilities.active.BladeJump;
import game.abilities.active.BostonShell;
import game.items.Gems;

public class BostonShellGem extends Gems {


    public BostonShellGem() {
        ability = new BostonShell();
        name = "Boston Shell Gem";
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
