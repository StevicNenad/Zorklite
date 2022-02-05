package game.items.gems;

import game.Attributes;
import game.abilities.active.Fireball;
import game.abilities.active.SoulSiphon;
import game.items.Gems;

public class SoulSiphonGem extends Gems {


    public SoulSiphonGem() {
        ability = new SoulSiphon();
        name = "Soul Siphone Gem";
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
