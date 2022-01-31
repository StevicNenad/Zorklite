package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.characters.Monster;

import java.util.ArrayList;

public class Slime extends Monster {

    public Slime() {
        name = "Slime Monster";
        damageType = DamageType.PHYSICAL;

        attributes = new Attributes(
                11,
                1,
                0,
                15,
                1,
                1,
                1,
                4,
                0,
                1,
                0.9,
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
