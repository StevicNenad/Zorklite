package game.characters.bosses;

import game.Ability;
import game.Attributes;
import game.DamageType;
import game.Item;
import game.abilities.active.Fireball;
import game.abilities.active.SoulSiphon;
import game.characters.Boss;

import java.util.ArrayList;

public class FalseGod extends Boss {

    public FalseGod() {
        name = "The Unborn";
        shortName = "God";
        damageType = DamageType.UNIVERSAL;
        flying = true;

        attributes = new Attributes(
                100,
                1,
                0,
                40,
                40,
                40,
                40,
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
        SoulSiphon soulSiphon = new SoulSiphon();
        Fireball fireball = new Fireball();
        actives.add(fireball);
        actives.add(soulSiphon);
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
