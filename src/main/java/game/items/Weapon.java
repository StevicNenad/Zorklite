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
                            attacks,
                            level;

    protected double        accuracy,
                            critChance,
                            aoeDamage;//Area of Effect/Cleave/Spill damage

    protected DamageType    damageType;
    protected AttackRange   attackRange;
    protected WeaponType    weaponType;
}