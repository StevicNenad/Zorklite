package game.characters.bosses;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.abilities.active.RecklessCharge;
import game.characters.Boss;

import java.util.ArrayList;

public class Underlord extends Boss {

    public Underlord() {
        name = "The demon lord, Reth'tirath";
        shortName = "Ret";
        damageType = DamageType.MAGICAL;
        flying = false;

        attributes = new Attributes(
                50,
                1,
                0,
                15,
                20,
                11,
                19,
                10,
                0,
                1,
                1,
                0.10,
                2.0,
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
        deathTokens = 500;
        passives = new ArrayList<Ability>();
        actives = new ArrayList<Ability>();
        loot = new ArrayList<Item>();

        calculateResistances();
        RecklessCharge recklessCharge = new RecklessCharge();
        actives.add(recklessCharge);
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
