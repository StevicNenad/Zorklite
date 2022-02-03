package game.items.gems;

import game.abilities.active.BostonShell;
import game.abilities.passive.DivineCombust;
import game.items.Gems;

public class DivineCombustGem extends Gems {


    public DivineCombustGem() {
        ability = new DivineCombust();
    }
}
