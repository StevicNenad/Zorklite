package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Repeater extends Weapon {

    public Repeater() {
        this.level = 1;
        this.damage = 6;
        this.aoeDamage = 0;
        this.accuracy = 0.80;
        this.attacks = 3;
        this.weaponType = WeaponType.PROJECTILE;
        this.attackRange = AttackRange.RANGED;
        this.type = "Weapon";
        this.name = "Repeating Pistol";
        this.damageType = DamageType.PHYSICAL;
    }
}
