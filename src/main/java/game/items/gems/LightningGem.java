package game.items.gems;

import game.Attributes;
import game.abilities.active.Fireball;
import game.abilities.passive.LightningSparks;
import game.items.Gems;

public class LightningGem extends Gems {


    public LightningGem() {
        ability = new LightningSparks();
        name = "Lightning Gem";
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
