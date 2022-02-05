package game.items.gems;

import game.Attributes;
import game.abilities.passive.EmberInfusion;
import game.abilities.passive.EscalatingViolence;
import game.items.Gems;

public class EscalatingViolenceGem extends Gems {


    public EscalatingViolenceGem() {
        ability = new EscalatingViolence();
        name = "Escalating Violence Gem";
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
