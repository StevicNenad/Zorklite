package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Shuriken extends Weapon {

    public Shuriken() {
        this.name = "Shurikens";

        attributes = new Attributes(
                25,
                1,
                3,
                0,
                0,
                0,
                0,
                0,
                6,
                1,
                0.80,
                0.50,
                1.80,
                0.00,
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
        attributes.setCritChance( attributes.getCritChance() + (0.02 * essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.10 * essences));
        attributes.setLevel(attributes.getLevel() + essences);

        if(attributes.getLevel() >= 5) {
            int scale = attributes.getLevel() / 5;
            for(int i = 0; i < scale; i++) {
                attributes.setProjectiles(attributes.getProjectiles() + 1);
            }
        }
    }
}
