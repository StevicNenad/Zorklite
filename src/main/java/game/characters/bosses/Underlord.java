package game.characters.bosses;

import game.Attributes;
import game.characters.Boss;

public class Underlord extends Boss {

    public Underlord() {
        name = "The demon lord, Reth'tirath";
        shortName = "Ret";

        attributes = new Attributes(
                50,
                1,
                0,
                15,
                5,
                10,
                10,
                10,
                0,
                1,
                1,
                0.10,
                2.0,
                0,
                0
        );

        maxHealth = attributes.calculateHealth();
        armorPoints = attributes.calculateArmor();
        maxMana = attributes.calculateMana();

        calculateResistances();
    }

    @Override
    public int getMaxHealth() {
        return super.getMaxHealth();
    }

    @Override
    public CharacterType getType() {
        return super.getType();
    }
}
