package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.characters.Monster;

import java.util.ArrayList;

public class Banshee extends Monster {

    public Banshee() {
        name = "Banshee";
        shortName = "Ban";
        damageType = DamageType.MAGICAL;
        flying = true;

        attributes = new Attributes(
                8,
                1,
                0,
                10,
                5,
                6,
                4,
                10,
                0,
                1,
                0.8,
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
        deathTokens = 45;

        passives = new ArrayList<Ability>();
        actives = new ArrayList<Ability>();
        loot = new ArrayList<Item>();

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
