package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.characters.Monster;

import java.util.ArrayList;

public class Sentry extends Monster {

    public Sentry() {
        name = "Sentry";
        shortName = "Sen";
        damageType = DamageType.PHYSICAL;
        flying = true;

        attributes = new Attributes(
                15,
                1,
                0,
                9,
                12,
                1,
                1,
                12,
                0,
                1,
                1,
                0.20,
                2.20,
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
        deathTokens = 65;

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
