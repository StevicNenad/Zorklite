package game.characters.monsters;

import game.Attributes;
import game.DamageType;
import game.characters.Monster;

public class Imp extends Monster {

    public Imp() {
        name = "Fiendish Imp";
        damageType = DamageType.PHYSICAL;

        attributes = new Attributes(
                20,
                1,
                0,
                12,
                5,
                6,
                16,
                10,
                1,
                1,
                0.10,
                1.80,
                0,
                0
        );

        maxHealth = attributes.calculateHealth();
        currentHealth = maxHealth;
        shieldPoints = attributes.calculateShield();
        currentShield = shieldPoints;
        armorPoints = attributes.calculateArmor();
        currentArmor = armorPoints;
        maxMana = attributes.calculateMana();
        currentMana = maxMana;

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
