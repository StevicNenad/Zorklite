package game.items.gems;

import game.abilities.active.Fireball;
import game.abilities.active.SoulSiphon;
import game.items.Gems;

public class SoulSiphonGem extends Gems {


    public SoulSiphonGem() {
        ability = new SoulSiphon();
    }
}
