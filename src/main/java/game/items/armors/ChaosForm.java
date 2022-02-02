package game.items.armors;

import game.Attributes;
import game.items.Armor;

import java.util.Random;

public class ChaosForm extends Armor {

    public ChaosForm() {
        this.name = "Chaos Form";

        attributes = new Attributes(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                -5,
                1,
                0,
                0,
                0,
                0,
                -0.1
        );

        this.itemType = ItemType.ARMOR;
        this.armorPoints = 35;
        this.speedModifier = 1.5;
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
