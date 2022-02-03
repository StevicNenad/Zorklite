package game.items.gems;

import game.abilities.active.DivineLight;
import game.abilities.active.Fireball;
import game.items.Gems;

public class FireballGem extends Gems {


    public FireballGem() {
        ability = new Fireball();
    }
}
