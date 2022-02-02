package game.items.armors;

import game.Attributes;
import game.items.Armor;

import java.util.Random;

public class DivineCuirrass extends Armor {

    public DivineCuirrass() {
        this.name = "Divine Cuirrass";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                -2,
                1,
                0,
                0,
                0,
                0,
                0.05
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 55;
        this.speedModifier = 0.9;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setStrength((int) (essences * 1.2));
        attributes.setIntelligence((int) (essences * 0.8));
        attributes.setAgility((int) (essences * 0.6));
        attributes.setSpeed((int) (essences * 0.2));
        attributes.setPerception((int) (essences * 1));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
