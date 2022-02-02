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
                0.10,
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
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.25 * essences)));
        attributes.setAccuracy( attributes.getAccuracy() + (0.025 + essences));
        attributes.setCritChance( attributes.getCritChance() + (0.01 * essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.1 * essences));
        attributes.setAoeDamage( attributes.getAoeDamage() + (0.025 + essences));
        attributes.setLevel(attributes.getLevel() + essences);
    }
}
