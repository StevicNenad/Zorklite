package game.items.armors;

import game.items.Armor;

public class ChaosForm extends Armor {

    public ChaosForm() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 35;
        this.damageReduction = -0.25;
        this.evasion = -0.1;
        this.speedModifier = 1.5;
        this.name = "Chaos Form";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
