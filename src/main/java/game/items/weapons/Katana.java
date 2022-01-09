package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Katana extends Weapon {

    public Katana() {
        this.level = 1;
        this.damage = 18;
        this.aoeDamage = 0;
        this.accuracy = 0.9;
        this.attacks = 1;
        this.weaponType = WeaponType.MELEE;
        this.attackRange = AttackRange.MELEE;
        this.type = "Weapon";
        this.name = "Dark Katana";
        this.damageType = DamageType.MAGICAL;
    }
}
