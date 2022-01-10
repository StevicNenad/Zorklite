package game.items.armors;

import game.items.Armor;

public class ScaleArmor extends Armor {

    public ScaleArmor() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 45;
        this.damageReduction = 0.25;
        this.evasion = 0.05;
        this.speedModifier = 0.9;
        this.name = "Scale Armor";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
