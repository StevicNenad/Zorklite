package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Spear extends Weapon {

    public Spear() {
        this.name = "Pike";

        attributes = new Attributes(
                77,
                1,
                0,
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

    @Override
    public void upgradeStats(int essences) {
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.20 * essences)));
        attributes.setAccuracy( attributes.getAccuracy() + (0.01 + essences));
        attributes.setCritChance( attributes.getCritChance() + (0.05 * essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.25 * essences));
        attributes.setEvasion( attributes.getEvasion() + (0.05 * essences));
        attributes.setLevel(attributes.getLevel() + essences);
    }
}
