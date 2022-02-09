package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.characters.Monster;

import java.util.ArrayList;

public class Behemoth extends Monster {

    public Behemoth() {
        name = "Behemoth";
        shortName = "Beh";
        damageType = DamageType.PHYSICAL;
        flying = false;

        attributes = new Attributes(
                25,
                1,
                0,
                12,
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
        deathTokens = 100;

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
