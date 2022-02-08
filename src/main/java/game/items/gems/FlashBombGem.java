package game.items.gems;

import game.Attributes;
import game.abilities.active.Fireball;
import game.abilities.active.FlashBomb;
import game.items.Gems;

public class FlashBombGem extends Gems {


    public FlashBombGem() {
        ability = new FlashBomb();
        name = "Flashbomb Gem";
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
