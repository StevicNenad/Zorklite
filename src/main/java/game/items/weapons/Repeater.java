package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Repeater extends Weapon {

    public Repeater() {
        this.name = "Repeating Pistol";

        attributes = new Attributes(
                6,
                3,
                1,
                0,
                0,
                0,
                0,
                0,
                1,
                0.80,
                0.35,
                1.50,
                0.00,
                0,
                0
        );

        this.weaponType = WeaponType.PROJECTILE;
        this.attackRange = AttackRange.RANGED;
        this.itemType = ItemType.WEAPON;
        this.damageType = DamageType.PHYSICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
