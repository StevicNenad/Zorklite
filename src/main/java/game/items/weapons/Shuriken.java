package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Shuriken extends Weapon {

    public Shuriken() {
        this.level = 1;
        this.damage = 4;
        this.aoeDamage = 0;
        this.accuracy = 0.80;
        this.attacks = 4;
        this.weaponType = WeaponType.PROJECTILE;
        this.attackRange = AttackRange.RANGED;
        this.itemType = ItemType.WEAPON;
        this.name = "Shurikens";
        this.damageType = DamageType.PHYSICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
