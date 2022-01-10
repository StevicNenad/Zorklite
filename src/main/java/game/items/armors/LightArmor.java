package game.items.armors;

import game.items.Armor;

public class LightArmor extends Armor {

    public LightArmor() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 10;
        this.damageReduction = 0.10;
        this.evasion = 0.10;
        this.speedModifier = 1.2;
        this.name = "Light Armor";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
