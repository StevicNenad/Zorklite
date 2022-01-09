package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Daggers extends Weapon {

    public Daggers() {
        this.level = 1;
        this.damage = 8;
        this.aoeDamage = 0;
        this.accuracy = 1.0;
        this.attacks = 2;
        this.weaponType = WeaponType.MELEE;
        this.attackRange = AttackRange.MELEE;
        this.type = "Weapon";
        this.name = "Daggers";
        this.damageType = DamageType.PHYSICAL;
    }
}
