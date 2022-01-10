package game.items.armors;

import game.items.Armor;

public class DivineCuirrass extends Armor {

    public DivineCuirrass() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 55;
        this.damageReduction = 0.4;
        this.evasion = 0.05;
        this.speedModifier = 0.9;
        this.name = "Divine Cuirrass";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
