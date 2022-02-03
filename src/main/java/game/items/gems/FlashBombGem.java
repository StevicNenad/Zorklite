package game.items.gems;

import game.abilities.active.Fireball;
import game.abilities.active.FlashBomb;
import game.items.Gems;

public class FlashBombGem extends Gems {


    public FlashBombGem() {
        ability = new FlashBomb();
    }
}
