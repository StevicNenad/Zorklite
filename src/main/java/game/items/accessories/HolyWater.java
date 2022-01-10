package game.items.accessories;

import game.items.Accessories;

public class HolyWater extends Accessories {
    private double      healthModifier,
                        experienceModifier;

    public HolyWater() {
        healthModifier = 0.3;
        experienceModifier = 2;
    }
}
