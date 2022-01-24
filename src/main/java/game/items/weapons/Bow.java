package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Bow extends Weapon {

    public Bow() {
        this.name = "Shortbow";

        attributes = new Attributes(
                15,
                1,
                1,
                0,
                0,
                0,
                0,
                0,
                1,
                0.95,
                0.20,
                1.85,
                0,
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
