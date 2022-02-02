package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Katana extends Weapon {

    public Katana() {
        this.name = "Dark Katana";

        attributes = new Attributes(
                80,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                1,
                0.90,
                0.25,
                2.00,
                0.00,
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

    @Override
    public void upgradeStats(int essences) {
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.20 * essences)));
        attributes.setAccuracy( attributes.getAccuracy() + (0.01 + essences));
        attributes.setCritChance( attributes.getCritChance() + (0.025 * essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.15 * essences));
        attributes.setEvasion( attributes.getEvasion() + (0.05 * essences));
        attributes.setLevel(attributes.getLevel() + essences);
    }
}
