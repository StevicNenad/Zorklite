package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class MagicRobe extends Armor {

    public MagicRobe() {
        this.name = "Magic Robe";
        description = "";
        itemType = ItemType.ARMOR;

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                10,
                0,
                0,
                0,
                0,
                1,
                0,
                0,
                0,
                0,
                0
        );

        armorPoints = 5;
        shieldPoints = 50;
        speedModifier = 1.3;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 0.1));
        attributes.setIntelligence((int) (essences * 2.5));
        attributes.setAgility((int) (essences * 0.1));
        attributes.setSpeed((int) (essences * 0.8));
        attributes.setPerception((int) (essences * 1.5));
        attributes.setStealth((int) (essences * 0.7));


        attributes.setLevel(attributes.getLevel() + essences);
    }
}
