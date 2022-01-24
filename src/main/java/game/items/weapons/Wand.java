package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Wand extends Weapon {

    public Wand() {
        this.level = 1;
        this.damage = 8;
        this.aoeDamage = 0;
        this.accuracy = 0.8;
        this.critChance = 1.00;
        this.critPercentage = 1.00;
        this.attacks = 1;
        this.weaponType = WeaponType.PROJECTILE;
        this.attackRange = AttackRange.RANGED;
        this.itemType = ItemType.WEAPON;
        this.name = "Magic Wand";
        this.damageType = DamageType.MAGICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
