package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Bow extends Weapon {

    public Bow() {
        this.name = "Shortbow";

        attributes = new Attributes(
                60,
                1,
                1,
                0,
                0,
                0,
                0,
                0,
                2,
                1,
                0.95,
                0.20,
                1.85,
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

    @Override
    public void upgradeStats(int essences) {
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.15 * essences)));
        attributes.setAccuracy( attributes.getAccuracy() + (0.01 + essences));
        attributes.setCritChance( attributes.getCritChance() + (0.01 * essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.05 * essences));
        attributes.setEvasion( attributes.getEvasion() + (0.05 * essences));
        attributes.setLevel(attributes.getLevel() + essences);
    }
}
