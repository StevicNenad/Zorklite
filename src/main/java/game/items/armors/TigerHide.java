package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class TigerHide extends Armor {

    public TigerHide() {
        this.name = "Tiger Hide";
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
                20,
                1,
                0,
                0,
                0,
                0,
                0.2
        );

        armorPoints = 10;
        speedModifier = 1.2;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 0.4));
        attributes.setIntelligence((int) (essences * 0.1));
        attributes.setAgility((int) (essences * 1.0));
        attributes.setSpeed((int) (essences * 2.5));
        attributes.setPerception((int) (essences * 1.5));
        attributes.setStealth((int) (essences * 2.0));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
