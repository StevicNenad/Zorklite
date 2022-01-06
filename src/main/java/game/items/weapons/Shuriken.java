package game.items.weapons;

import game.items.Weapon;

public class Shuriken extends Weapon {

    public Shuriken() {
        this.level = 1;
        this.damage = 4;
        this.aoeDamage = 0;
        this.accuracy = 0.80;
        this.attacks = 4;
        this.armorPenetration = 0.50;
        this.ranged = true;
        this.type = "Weapon";
        this.name = "Shurikens";
        this.physicalDamage = true;
    }
}
