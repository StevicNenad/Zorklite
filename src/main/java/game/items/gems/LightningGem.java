package game.items.gems;

import game.abilities.active.Fireball;
import game.abilities.passive.LightningSparks;
import game.items.Gems;

public class LightningGem extends Gems {


    public LightningGem() {
        ability = new LightningSparks();
    }
}
