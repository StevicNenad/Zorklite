package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class LightArmor extends Armor {

    public LightArmor() {
        this.name = "Light Armor";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                0,
                0,
                0,
                0,
                0.10
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 10;
        this.speedModifier = 1.2;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 0.3));
        attributes.setIntelligence((int) (essences * 0.5));
        attributes.setAgility((int) (essences * 1.5));
        attributes.setSpeed((int) (essences * 0.4));
        attributes.setPerception((int) (essences * 0.6));
        attributes.setStealth((int) (essences * 1));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
