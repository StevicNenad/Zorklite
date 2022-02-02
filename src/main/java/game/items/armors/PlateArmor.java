package game.items.armors;

import game.Attributes;
import game.items.Armor;

public class PlateArmor extends Armor {

    public PlateArmor() {
        this.name = "Plate Armor";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                -15,
                1,
                0,
                0,
                0,
                0,
                0
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 100;
        this.speedModifier = 0.5;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 3));
        attributes.setIntelligence((int) (essences * 0.1));
        attributes.setAgility((int) (essences * 0.1));
        attributes.setSpeed((int) (essences * -2));
        attributes.setPerception((int) (essences * 0.1));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
