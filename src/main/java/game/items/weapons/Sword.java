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

    @Override
    public void upgradeStats(int essences) {
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.33 * essences)));
        attributes.setAccuracy( attributes.getAccuracy() + (0.005 + essences));
        attributes.setCritChance( attributes.getCritChance() + (0.025 * essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.20 * essences));
        attributes.setLevel(attributes.getLevel() + essences);
    }
}
