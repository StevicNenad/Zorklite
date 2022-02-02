package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Greatsword extends Weapon {

    public Greatsword() {
        this.name = "Greatsword";

        attributes = new Attributes(
                150,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                -6,
                1,
                0.65,
                0,
                2.20,
                0.50,
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
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.20 * essences)));
        attributes.setAccuracy( attributes.getAccuracy() + (0.033 + essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.25 * essences));
        attributes.setLevel(attributes.getLevel() + essences);
        attributes.setAoeDamage( attributes.getAoeDamage() + (0.05 + essences));
    }
}
