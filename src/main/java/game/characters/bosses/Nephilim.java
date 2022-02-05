package game.characters.bosses;

import game.Attributes;
import game.characters.Boss;

public class Nephilim extends Boss {

    public Nephilim() {
        name = "Irizael, the unmaker";
        shortName = "Iri";

        attributes = new Attributes(
                25,
                2,
                0,
                15,
                5,
                23,
                20,
                10,
                0,
                1,
                0.8,
                0.10,
                1.80,
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
