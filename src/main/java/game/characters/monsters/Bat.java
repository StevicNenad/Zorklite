package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.Character;
import game.DamageType;
import game.characters.Monster;

import java.util.ArrayList;

public class Bat extends Monster {

    public Bat() {
        name = "Hellbat";
        shortName = "Bat";
        damageType = DamageType.PHYSICAL;

        attributes = new Attributes(
                3,
                2,
                0,
                5,
                6,
                10,
                8,
                10,
                0,
                1,
                1,
                0.15,
                2.00,
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
        deathTokens = 30;

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
