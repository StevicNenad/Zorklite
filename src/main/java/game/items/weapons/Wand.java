package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Wand extends Weapon {

    public Wand() {
        this.name = "Magic Wand";

        attributes = new Attributes(
                8,
                1,
                1,
                0,
                0,
                0,
                0,
                0,
                1,
                0.80,
                0,
                2.00,
                0.00,
                0,
                0
        );

        this.weaponType = WeaponType.PROJECTILE;
        this.attackRange = AttackRange.RANGED;
        this.itemType = ItemType.WEAPON;
        this.damageType = DamageType.MAGICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
