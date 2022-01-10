package game.items;

import game.Item;

public class Armor extends Item {
    protected int       armorPoints,
                        level,
                        strBonus,
                        agiBonus,
                        intBonus,
                        stealthBonus;

    protected double    damageReduction,
                        speedModifier,
                        evasion,
                        experienceModifier;
}
