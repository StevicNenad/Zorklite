package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.characters.Monster;

import java.util.ArrayList;

public class Spectre extends Monster {

    public Spectre() {
        name = "Spectre";
        shortName = "Spc";
        damageType = DamageType.MAGICAL;

        attributes = new Attributes(
                5,
                1,
                0,
                9,
                7,
                10,
                10,
                12,
                0,
                1,
                0.85,
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
        deathTokens = 40;

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
