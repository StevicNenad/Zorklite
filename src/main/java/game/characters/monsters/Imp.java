package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.characters.Monster;

import java.util.ArrayList;

public class Imp extends Monster {

    public Imp() {
        name = "Fiendish Imp";
        shortName = "Imp";
        damageType = DamageType.PHYSICAL;
        flying = false;

        attributes = new Attributes(
                4,
                3,
                0,
                4,
                5,
                6,
                16,
                10,
                0,
                1,
                1,
                0.25,
                2.00,
                0,
                0.4
        );

        maxHealth = attributes.calculateHealth();
        currentHealth = maxHealth;
        shieldPoints = attributes.calculateShield();
        currentShield = shieldPoints;
        armorPoints = attributes.calculateArmor();
        currentArmor = armorPoints;
        maxMana = attributes.calculateMana();
        currentMana = maxMana;
        deathTokens = 20;

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
