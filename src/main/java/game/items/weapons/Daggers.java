package game.items.weapons;

import game.AttackRange;
import game.Attributes;
import game.DamageType;
import game.items.Weapon;

public class Daggers extends Weapon {

    public Daggers() {
        this.name = "Daggers";

        attributes = new Attributes(
                35,
                2,
                0,
                0,
                0,
                0,
                0,
                0,
                4,
                1,
                1.00,
                0.30,
                2.50,
                0,
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
        attributes.setDamage( attributes.getDamage() + (int) (attributes.getDamage() * (0.10 * essences)));
        attributes.setStealth(attributes.getStealth() + essences);
        attributes.setCritChance( attributes.getCritChance() + (0.05 * essences));
        attributes.setCritPercentage(attributes.getCritPercentage() + (0.5 * essences));
        attributes.setEvasion( attributes.getEvasion() + (0.05 * essences));
        attributes.setLevel(attributes.getLevel() + essences);

        if(attributes.getLevel() >= 5) {
            int scale = attributes.getLevel() / 5;
            for(int i = 0; i < scale; i++) {
                attributes.setAttacks(attributes.getAttacks() + essences);
            }
        }
    }
}
