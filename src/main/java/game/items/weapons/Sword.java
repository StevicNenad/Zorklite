package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Sword extends Weapon {

    public Sword() {
        this.name = "Broadsword";

        attributes = new Attributes(
                60,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                0.95,
                0.1,
                2.50,
                0.00,
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
