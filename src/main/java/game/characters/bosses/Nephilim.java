package game.characters.bosses;

import game.Attributes;
import game.characters.Boss;

public class Nephilim extends Boss {

    public Nephilim() {
        name = "Irizael, the unmaker";
        shortName = "Iri";

        attributes = new Attributes(
                20,
                1,
                0,
                12,
                5,
                6,
                8,
                10,
                0,
                1,
                1,
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
