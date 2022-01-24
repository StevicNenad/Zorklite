package game.items;

import game.Item;

public class Armor extends Item {
    protected int       armorPoints,
                        strBonus,
                        agiBonus,
                        intBonus,
                        stealthBonus;

    protected double    damageReduction,
                        speedModifier,
                        evasion,
                        experienceModifier;

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
