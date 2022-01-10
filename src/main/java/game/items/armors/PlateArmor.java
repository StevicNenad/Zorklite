package game.items.armors;

import game.items.Armor;

public class PlateArmor extends Armor {

    public PlateArmor() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 100;
        this.damageReduction = 0.30;
        this.evasion = 0;
        this.speedModifier = 0.5;
        this.name = "Plate Armor";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
