package game.characters.monsters;

import game.Attributes;
import game.characters.Monster;

public class Spectre extends Monster {

    public Spectre() {
        name = "Spectre";

        attributes = new Attributes(
                20,
                1,
                0,
                12,
                5,
                6,
                8,
                10,
                1,
                1,
                0.10,
                1.80,
                0,
                0.10,
                0
        );

        maxHealth = attributes.calculateHealth();
        currentHealth = maxHealth;
        shieldPoints = attributes.calculateShield();
        armorPoints = attributes.calculateArmor();
        maxMana = attributes.calculateMana();
        currentMana = maxMana;
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
