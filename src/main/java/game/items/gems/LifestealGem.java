package game.items.gems;

import game.Attributes;
import game.abilities.passive.Lifesteal;
import game.items.Gems;

public class LifestealGem extends Gems {


    public LifestealGem() {
        ability = new Lifesteal();
        name = "Lifesteal Gem";
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
