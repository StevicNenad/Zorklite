package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Katana extends Weapon {

    public Katana() {
        this.name = "Dark Katana";

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
                0.25,
                2.00,
                0.00,
                0,
                0
        );

        this.weaponType = WeaponType.MELEE;
        this.attackRange = AttackRange.MELEE;
        this.itemType = ItemType.WEAPON;
        this.damageType = DamageType.MAGICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
