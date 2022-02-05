package game.items.gems;

import game.Attributes;
import game.abilities.active.DivineLight;
import game.abilities.active.Fireball;
import game.items.Gems;

public class FireballGem extends Gems {


    public FireballGem() {
        ability = new Fireball();
        name = "Fireball Gem";
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
