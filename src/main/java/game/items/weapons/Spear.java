package game.items.weapons;

import game.items.Weapon;

public class Spear extends Weapon {

    public Spear() {
        this.level = 1;
        this.damage = 18;
        this.aoeDamage = 0;
        this.accuracy = 0.9;
        this.attacks = 1;
        this.armorPenetration = 0.30;
        this.ranged = false;
        this.type = "Weapon";
        this.name = "Pike";
        this.physicalDamage = true;
    }
}
