package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Spear extends Weapon {

    public Spear() {
        this.name = "Pike";

        attributes = new Attributes(
                18,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                0.90,
                0.1,
                2.10,
                0.00,
                0,
                0
        );

        this.weaponType = WeaponType.MELEE;
        this.attackRange = AttackRange.RANGED;
        this.itemType = ItemType.WEAPON;
        this.damageType = DamageType.PHYSICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
