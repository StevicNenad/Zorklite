package game.items.armors;

import game.items.Armor;

public class TigerHide extends Armor {

    public TigerHide() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 10;
        this.damageReduction = 0.15;
        this.evasion = 0.20;
        this.speedModifier = 1.2;
        this.name = "Tiger Hide";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
