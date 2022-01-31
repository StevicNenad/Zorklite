package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Axe extends Weapon {

    public Axe() {
        this.name = "Battleaxe";

        attributes = new Attributes(
                75,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                0.80,
                0.10,
                2.00,
                0.1,
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
