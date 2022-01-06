package game.items.armors;

import game.items.Armor;

public class LightArmor extends Armor {

    public LightArmor() {
        this.level = 1;
        this.armorPoints = 25;
        this.damageReduction = 0.10;
        this.evasion = 0;
        this.speedModifier = 1;
    }
}
