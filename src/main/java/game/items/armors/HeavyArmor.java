package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class HeavyArmor extends Armor {

    public HeavyArmor() {
        this.name = "Heavy Armor";
        description = "";
        itemType = ItemType.ARMOR;

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                -10,
                1,
                0,
                0,
                0,
                0,
                0
        );

        armorPoints = 25;
        speedModifier = 1;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 2));
        attributes.setIntelligence((int) (essences * 0.2));
        attributes.setAgility((int) (essences * 0.1));
        attributes.setSpeed((int) (essences * -0.2));
        attributes.setPerception((int) (essences * 0.1));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
