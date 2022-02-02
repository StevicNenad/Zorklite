package game.characters.monsters;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.characters.Monster;

import java.util.ArrayList;

public class Arachnid extends Monster {

    public Arachnid() {
        name = "Cursed Spider";
        shortName = "Spr";
        damageType = DamageType.PHYSICAL;

        attributes = new Attributes(
                5,
                1,
                0,
                7,
                5,
                6,
                6,
                15,
                0,
                1,
                0.9,
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
        deathTokens = 35;

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
