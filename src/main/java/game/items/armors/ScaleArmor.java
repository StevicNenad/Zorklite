package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class ScaleArmor extends Armor {

    public ScaleArmor() {
        this.name = "Scale Armor";
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
                8,
                1,
                0,
                0,
                0,
                0,
                0.05
        );

        armorPoints = 45;
        speedModifier = 0.9;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 1));
        attributes.setIntelligence((int) (essences * 1));
        attributes.setAgility((int) (essences * 1));
        attributes.setSpeed((int) (essences * 1));
        attributes.setPerception((int) (essences * 1));
        attributes.setStealth((int) (essences * 1));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
