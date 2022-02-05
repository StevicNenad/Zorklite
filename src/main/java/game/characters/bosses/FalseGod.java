package game.characters.bosses;

import game.Ability;
import game.Attributes;
import game.Item;
import game.characters.Boss;

import java.util.ArrayList;

public class FalseGod extends Boss {

    public FalseGod() {
        name = "The Unborn";
        shortName = "God";

        attributes = new Attributes(
                100,
                1,
                0,
                25,
                25,
                25,
                25,
                25,
                0,
                1,
                1,
                0.25,
                2.5,
                0,
                0.3
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
