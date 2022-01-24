package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Spear extends Weapon {

    public Spear() {
        this.level = 1;
        this.damage = 18;
        this.aoeDamage = 0;
        this.accuracy = 0.9;
        this.critChance = 1.1;
        this.critPercentage = 2.00;
        this.attacks = 1;
        this.weaponType = WeaponType.MELEE;
        this.attackRange = AttackRange.RANGED;
        this.itemType = ItemType.WEAPON;
        this.name = "Pike";
        this.damageType = DamageType.PHYSICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
