package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Bow extends Weapon {

    public Bow() {
        this.level = 1;
        this.damage = 15;
        this.aoeDamage = 0;
        this.accuracy = 0.95;
        this.attacks = 1;
        this.weaponType = WeaponType.PROJECTILE;
        this.attackRange = AttackRange.RANGED;
        this.type = "Weapon";
        this.name = "Shortbow";
        this.damageType = DamageType.PHYSICAL;
    }
}
