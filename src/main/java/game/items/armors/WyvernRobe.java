package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class WyvernRobe extends Armor {

    public WyvernRobe() {
        this.name = "Wyvern Robe";
        description = "";
        itemType = ItemType.ARMOR;

        attributes = new Attributes(
                0,
                0,
                0,
                5,
                6,
                5,
                3,
                6,
                10,
                1,
                0,
                0,
                0,
                0,
                0
        );

        armorPoints = 10;
        speedModifier = 1.5;
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
