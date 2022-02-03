package game.items.gems;

import game.abilities.passive.EmberInfusion;
import game.abilities.passive.EscalatingViolence;
import game.items.Gems;

public class EscalatingViolenceGem extends Gems {


    public EscalatingViolenceGem() {
        ability = new EscalatingViolence();
    }
}
