package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Wand extends Weapon {

    public Wand() {
        this.name = "Magic Wand";

        attributes = new Attributes(
                100,
                1,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                0.80,
                0,
                2.00,
                0.00,
                0
        );

        this.weaponType = WeaponType.PROJECTILE;
        this.attackRange = AttackRange.RANGED;
        this.itemType = ItemType.WEAPON;
        this.damageType = DamageType.MAGICAL;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void upgradeStats(int essences) {
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.1 * essences)));
        attributes.setAccuracy( attributes.getAccuracy() + (0.01 + essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.20 * essences));
        attributes.setLevel(attributes.getLevel() + essences);

        if(attributes.getLevel() >= 3) {
            int scale = attributes.getLevel() / 4;
            for(int i = 0; i < scale; i++) {
                attributes.setAttacks(attributes.getAttacks() + 1);
            }
        }
    }
}
