package game.items;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.Item;

public class Weapon extends Item {
    protected enum WeaponType {
        MELEE,
        PROJECTILE
    }

    protected WeaponType    weaponType;
    protected DamageType    damageType;
    protected AttackRange   attackRange;

    @Override
    public String getName() {
        return super.getName();
    }
}