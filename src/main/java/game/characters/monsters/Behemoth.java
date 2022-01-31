package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.characters.Monster;

import java.util.ArrayList;

public class Behemoth extends Monster {

    public Behemoth() {
        name = "Behemoth";
        damageType = DamageType.PHYSICAL;

        attributes = new Attributes(
                25,
                1,
                0,
                20,
                3,
                2,
                2,
                4,
                0,
                1,
                0.6,
                0.05,
                2.50,
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

        passives = new ArrayList<Ability>();
        actives = new ArrayList<Ability>();

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
