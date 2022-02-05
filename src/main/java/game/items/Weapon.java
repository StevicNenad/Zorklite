package game.items;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.Item;

public class Weapon extends Item {
    public enum WeaponType {
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

    @Override
    public Attributes getAttributes() {
        return super.getAttributes();
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    @Override
    public void upgradeStats(int essences) {
        super.upgradeStats(essences);
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}