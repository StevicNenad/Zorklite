package game.items.armors;

import game.items.Armor;

public class ShadowChainmail extends Armor {

    public ShadowChainmail() {
        this.itemType = ItemType.ARMOR;
        this.level = 1;
        this.armorPoints = 75;
        this.damageReduction = 0.33;
        this.evasion = -0.2;
        this.speedModifier = 0.7;
        this.name = "Shadow Chainmail";
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
