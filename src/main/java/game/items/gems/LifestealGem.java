package game.items.gems;

import game.abilities.passive.Lifesteal;
import game.items.Gems;

public class LifestealGem extends Gems {


    public LifestealGem() {
        ability = new Lifesteal();
    }
}
