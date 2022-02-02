package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.characters.Monster;

import java.util.ArrayList;

public class FireLizard extends Monster {

    public FireLizard() {
        name = "Fire Lizard";
        shortName = "Liz";
        damageType = DamageType.PHYSICAL;

        attributes = new Attributes(
                7,
                1,
                0,
                8,
                8,
                8,
                8,
                8,
                0,
                1,
                0.8,
                0.10,
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
        deathTokens = 40;

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
