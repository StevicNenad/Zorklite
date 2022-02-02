package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class ShadowChainmail extends Armor {

    public ShadowChainmail() {
        this.name = "Shadow Chainmail";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                15,
                1,
                0,
                0,
                0,
                0,
                -0.2
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 75;
        this.speedModifier = 0.7;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 0.2));
        attributes.setIntelligence((int) (essences * 0.1));
        attributes.setAgility((int) (essences * 1.5));
        attributes.setSpeed((int) (essences * 2.0));
        attributes.setPerception((int) (essences * 2.0));
        attributes.setStealth((int) (essences * 2.0));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
