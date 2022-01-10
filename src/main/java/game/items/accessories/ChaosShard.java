package game.items.accessories;

import game.items.Accessories;

public class ChaosShard extends Accessories {
    private double      healthModifier,
                        experienceModifier;

    public ChaosShard() {
        healthModifier = 0.3;
        experienceModifier = 2;
    }
}
