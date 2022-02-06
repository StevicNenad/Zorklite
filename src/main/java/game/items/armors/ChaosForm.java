package game.items.armors;

import game.Attributes;
import game.items.Armor;

import java.util.Random;

public class ChaosForm extends Armor {

    public ChaosForm() {
        name = "Chaos Form";
        description = "";
        itemType = ItemType.ARMOR;

        attributes = new Attributes(
                12,
                0,
                0,
                4,
                1,
                0,
                4,
                2,
                -5,
                1,
                0,
                0,
                0,
                0,
                -0.1
        );

        armorPoints = 35;
        speedModifier = 1.5;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        Random rn = new Random();
        double factor = rn.nextDouble();
        attributes.setStrength((int) (essences * factor));

        factor = rn.nextDouble();
        attributes.setIntelligence((int) (essences * factor));

        factor = rn.nextDouble();
        attributes.setAgility((int) (essences * factor));

        factor = rn.nextDouble();
        attributes.setSpeed((int) (essences * factor));

        factor = rn.nextDouble();
        attributes.setStealth((int) (essences * factor));

        factor = rn.nextDouble();
        attributes.setPerception((int) (essences * factor));

        attributes.setLevel(attributes.getLevel() + essences);
    }
}
