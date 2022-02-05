package game.items.gems;

import game.Attributes;
import game.abilities.active.BostonShell;
import game.abilities.active.DivineLight;
import game.items.Gems;

public class DivineLightGem extends Gems {


    public DivineLightGem() {
        ability = new DivineLight();
        name = "Divine Light Gem";
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
