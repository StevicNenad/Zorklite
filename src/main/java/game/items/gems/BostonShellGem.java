package game.items.gems;

import game.abilities.active.BladeJump;
import game.abilities.active.BostonShell;
import game.items.Gems;

public class BostonShellGem extends Gems {


    public BostonShellGem() {
        ability = new BostonShell();
    }
}
