package game.items.weapons;

import game.items.Weapon;

public class Daggers extends Weapon {

    public Daggers() {
        this.level = 1;
        this.damage = 8;
        this.aoeDamage = 0;
        this.accuracy = 1.0;
        this.attacks = 2;
        this.armorPenetration = 0.20;
        this.ranged = false;
        this.type = "Weapon";
        this.name = "Daggers";
        this.physicalDamage = true;
    }
}
