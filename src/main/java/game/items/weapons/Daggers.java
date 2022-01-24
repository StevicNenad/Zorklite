package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Daggers extends Weapon {

    public Daggers() {
        this.name = "Daggers";

        attributes = new Attributes(
                8,
                2,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                1.00,
                0.30,
                2.50,
                0,
                0,
                0
        );

        this.weaponType = WeaponType.MELEE;
        this.attackRange = AttackRange.MELEE;
        this.itemType = ItemType.WEAPON;
        this.damageType = DamageType.PHYSICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
