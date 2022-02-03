package game.items;

import game.Attributes;
import game.Item;

public class Armor extends Item {
    protected int       armorPoints,
                        shieldPoints;

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Attributes getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void upgradeStats(int essences) {
        super.upgradeStats(essences);
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    public int getShieldPoints() {
        return shieldPoints;
    }
}
