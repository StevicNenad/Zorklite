package game.items;

import game.DamageType;
import game.Item;

public class Weapon extends Item {
    protected enum AttackRange {
        MELEE,
        RANGED
    }
    protected enum WeaponType {
        MELEE,
        PROJECTILE
    }

    protected int           damage,
                            attacks;

    protected double        accuracy,
                            critChance,
                            critPercentage,
                            aoeDamage;//Area of Effect/Cleave/Spill damage

    protected DamageType    damageType;
    protected AttackRange   attackRange;
    protected WeaponType    weaponType;

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    public int getDamage() {
        return damage;
    }

    public double getCritChance() {
        return critChance;
    }

    public double getCritPercentage() {
        return critPercentage;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}