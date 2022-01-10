package game.items.armors;

import game.items.Armor;

public class MagicRobe extends Armor {

    public MagicRobe() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 5;
        this.damageReduction = 0.10;
        this.evasion = 0;
        this.speedModifier = 1.3;
        this.name = "Magic Robe";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
