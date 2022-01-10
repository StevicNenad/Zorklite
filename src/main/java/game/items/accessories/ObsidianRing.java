package game.items.accessories;

import game.items.Accessories;

public class ObsidianRing extends Accessories {
    private double      healthModifier,
                        experienceModifier;

    public ObsidianRing() {
        healthModifier = 0.3;
        experienceModifier = 2;
    }
}
