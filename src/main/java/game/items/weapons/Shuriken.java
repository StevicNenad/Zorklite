package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Shuriken extends Weapon {

    public Shuriken() {
        this.name = "Shurikens";

        attributes = new Attributes(
                6,
                1,
                3,
                0,
                0,
                0,
                0,
                0,
                1,
                0.80,
                0.50,
                1.80,
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
