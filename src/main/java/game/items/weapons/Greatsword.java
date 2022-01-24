package game.items.weapons;

import game.DamageType;
import game.items.Weapon;

public class Greatsword extends Weapon {

    public Greatsword() {
        this.level = 1;
        this.damage = 45;
        this.aoeDamage = 0.30;
        this.accuracy = 0.65;
        this.critChance = 1.05;
        this.critPercentage = 2.20;
        this.attacks = 1;
        this.weaponType = WeaponType.MELEE;
        this.attackRange = AttackRange.MELEE;
        this.itemType = ItemType.WEAPON;
        this.name = "Greatsword";
        this.damageType = DamageType.PHYSICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
