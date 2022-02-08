package game.characters.bosses;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.abilities.active.HailMary;
import game.characters.Boss;

import java.util.ArrayList;

public class Nephilim extends Boss {

    public Nephilim() {
        name = "Irizael, the unmaker";
        shortName = "Iri";
        damageType = DamageType.PHYSICAL;
        flying = true;

        attributes = new Attributes(
                34,
                2,
                0,
                15,
                15,
                23,
                20,
                10,
                0,
                1,
                0.8,
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
        deathTokens = 500;
        passives = new ArrayList<Ability>();
        actives = new ArrayList<Ability>();
        loot = new ArrayList<Item>();

        calculateResistances();

        HailMary hailMary = new HailMary();
        actives.add(hailMary);
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
