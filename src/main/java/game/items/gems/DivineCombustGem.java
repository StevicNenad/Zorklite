package game.items.gems;

import game.Attributes;
import game.abilities.active.BostonShell;
import game.abilities.passive.DivineCombust;
import game.items.Gems;

public class DivineCombustGem extends Gems {


    public DivineCombustGem() {
        ability = new DivineCombust();
        name = "Divine Combustion Gem";
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
