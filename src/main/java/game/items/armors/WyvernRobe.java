package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class WyvernRobe extends Armor {

    public WyvernRobe() {
        this.name = "Wyvern Robe";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                10,
                1,
                0,
                0,
                0,
                0,
                0
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 10;
        this.speedModifier = 1.5;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 0.5));
        attributes.setIntelligence((int) (essences * 3));
        attributes.setAgility((int) (essences * 0.5));
        attributes.setSpeed((int) (essences * 0.5));
        attributes.setPerception((int) (essences * 2.0));
        attributes.setStealth((int) (essences * 1));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
