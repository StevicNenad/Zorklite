package game.items.weapons;

import game.items.Weapon;

public class Bow extends Weapon {

    public Bow() {
        this.level = 1;
        this.damage = 15;
        this.aoeDamage = 0;
        this.accuracy = 0.95;
        this.attacks = 1;
        this.armorPenetration = 0;
        this.ranged = true;
        this.type = "Weapon";
        this.name = "Shortbow";
        this.physicalDamage = true;
    }
}
