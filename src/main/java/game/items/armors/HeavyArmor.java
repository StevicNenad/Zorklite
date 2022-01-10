package game.items.armors;

import game.items.Armor;

public class HeavyArmor extends Armor {

    public HeavyArmor() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 25;
        this.damageReduction = 0.10;
        this.evasion = 0;
        this.speedModifier = 1;
        this.name = "Heavy Armor";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
