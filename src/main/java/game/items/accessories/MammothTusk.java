package game.items.accessories;

import game.items.Accessories;

public class MammothTusk extends Accessories {
    private double      healthModifier,
                        experienceModifier;

    public MammothTusk() {
        healthModifier = 0.3;
        experienceModifier = 2;
    }
}
